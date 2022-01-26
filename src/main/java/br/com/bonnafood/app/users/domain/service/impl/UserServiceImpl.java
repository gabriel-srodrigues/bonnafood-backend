package br.com.bonnafood.app.users.domain.service.impl;

import br.com.bonnafood.app.users.domain.exception.UserNotFoundException;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import br.com.bonnafood.app.users.domain.repository.UserSpecification;
import br.com.bonnafood.app.users.domain.service.UserActivationService;
import br.com.bonnafood.app.users.domain.service.UserAvatarService;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import br.com.bonnafood.app.users.domain.service.UserOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserCrudService, UserActivationService, UserOnboardingService, UserAvatarService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public BonnaUser findByIdOrThrows(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public BonnaUser save(BonnaUser user) {
        return user.isNewUser() ? this.create(user) : this.update(user);
    }

    @Override
    public void updatePassword(String id, String oldPassword, String newPassword) {
        BonnaUser user = findByIdOrThrows(id);
        if (!isRightPassword(user.getPassword(), oldPassword)) {
            throw new RuntimeException("");
        }
        Consumer<BonnaUser> userConsumer = bonnaUser -> bonnaUser.setPassword(encoder.encode(newPassword));
        userConsumer.accept(user);

        this.save(user);
    }

    @Override
    public Page<BonnaUser> search(UserFilter userFilter, Pageable page) {
        return userRepository.findAll(UserSpecification.usingUserFilter(userFilter), page);
    }

    @Transactional
    BonnaUser update(BonnaUser user) {
        userRepository.detach(user);

        BonnaUser existingUser = findByIdOrThrows(user.getId());
        userRepository.detach(existingUser);

        validateSensitiveFieldsUpdate(user, existingUser);
        validateUserEmailAvailability(user);
        validateRoleGrantPolicies(user);

        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    @Transactional
    BonnaUser create(BonnaUser user) {
        userRepository.detach(user);
        validateUserEmailAvailability(user);
        validateRoleGrantPolicies(user);

        user.setPassword(encoder.encode(user.getPassword()));

        user.activate();
        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    private void validateUserEmailAvailability(BonnaUser user) {}
    private void validateRoleGrantPolicies(BonnaUser user) {}
    private void validateSensitiveFieldsUpdate(BonnaUser userToUpdate, BonnaUser existingUser) {}

    private boolean isRightPassword(String encodedPassword, String currentPassword) {
        return encoder.matches(currentPassword, encodedPassword);
    }

}
