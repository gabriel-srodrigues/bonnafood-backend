package br.com.bonnafood.app.users.domain.service.impl;

import br.com.bonnafood.app.users.domain.exception.UserNotFoundException;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import br.com.bonnafood.app.users.domain.service.UserActivationService;
import br.com.bonnafood.app.users.domain.service.UserAvatarService;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import br.com.bonnafood.app.users.domain.service.UserOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserCrudService, UserActivationService, UserOnboardingService, UserAvatarService {
    private final UserRepository userRepository;

    @Override
    public BonnaUser findByIdOrThrows(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public BonnaUser save(BonnaUser user) {
        return user.isNewUser() ? this.create(user) : this.update(user);
    }

    @Transactional
    BonnaUser update(BonnaUser user) {
        userRepository.detach(user);

        BonnaUser existingUser = findByIdOrThrows(user.getId());
        userRepository.detach(existingUser);

        validateSensitiveFieldsUpdate(user, existingUser);
        validateUserEmailAvailability(user);
        validateRoleGrantPolicies(user);

        applyUserOnSkills(user);

        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    @Transactional
    BonnaUser create(BonnaUser user) {
        userRepository.detach(user);
        validateUserEmailAvailability(user);
        validateRoleGrantPolicies(user);

        applyUserOnSkills(user);

        user.activate();
        user = userRepository.save(user);
        userRepository.flush();

        return user;
    }

    private void validateUserEmailAvailability(BonnaUser user) {}
    private void validateRoleGrantPolicies(BonnaUser user) {}
    private void applyUserOnSkills(BonnaUser user) {}
    private void validateSensitiveFieldsUpdate(BonnaUser userToUpdate, BonnaUser existingUser) {}
}
