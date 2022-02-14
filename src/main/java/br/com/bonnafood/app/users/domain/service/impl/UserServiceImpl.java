package br.com.bonnafood.app.users.domain.service.impl;

import br.com.bonnafood.app.common.domain.model.BusinessException;
import br.com.bonnafood.app.security.BonnafoodSecurity;
import br.com.bonnafood.app.users.domain.exception.UpdatePasswordNotAllowedException;
import br.com.bonnafood.app.users.domain.exception.UserNotFoundException;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import br.com.bonnafood.app.users.domain.repository.UserSpecification;
import br.com.bonnafood.app.users.domain.service.UserActivationService;
import br.com.bonnafood.app.users.domain.service.UserAvatarService;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import br.com.bonnafood.app.users.domain.service.UserOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserCrudService, UserActivationService, UserOnboardingService, UserAvatarService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final BonnafoodSecurity security;

    @Override
    public User findByIdOrThrows(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User user) {
        return user.isNewUser() ? this.create(user) : this.update(user);
    }

    @Override
    public void updatePassword(String id, String oldPassword, String newPassword) {
        User user = findByIdOrThrows(id);
        if (!isRightPassword(user.getPassword(), oldPassword) || canUpdatePassword()) {
            throw new UpdatePasswordNotAllowedException("The password entered doesn't match with user's password.");
        }
        Consumer<User> userConsumer = bonnaUser -> bonnaUser.setPassword(encoder.encode(newPassword));
        userConsumer.accept(user);

        this.save(user);
    }

    @Override
    public Page<User> search(UserFilter userFilter, Pageable page) {
        return userRepository.findAll(UserSpecification.usingUserFilter(userFilter), page);
    }

    @Transactional
    User update(User user) {
        userRepository.detach(user);

        validateUserEmailAvailability(user);

        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    @Transactional
    User create(User user) {
        userRepository.detach(user);
        validateUserEmailAvailability(user);

        user.setPassword(encoder.encode(user.getPassword()));

        user.activate();
        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    private boolean isEmailInUse(User user) {
        return userRepository.existsByEmailAndDifferentUserId(user.getEmail(), user.getId());
    }

    private boolean hasUserWithThisEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private void validateUserEmailAvailability(User user) {
        if (user.isNotNew() && isEmailInUse(user) || user.isNew() && hasUserWithThisEmail(user.getEmail())) {
            throw new BusinessException("Already exists any user with e-mail %s".formatted(user.getEmail()));
        }
    }

    private boolean isRightPassword(String encodedPassword, String currentPassword) {
        return encoder.matches(currentPassword, encodedPassword);
    }

    private boolean canUpdatePassword() {
        return security.canUpdateAnyPassword();
    }
}
