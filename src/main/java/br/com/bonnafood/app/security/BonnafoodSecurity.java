package br.com.bonnafood.app.security;

import br.com.bonnafood.app.users.domain.exception.UserNotFoundException;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public abstract class BonnafoodSecurity {
    private final UserRepository userRepository;

    public abstract String getUserId();

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Optional<User> getUserAuthenticated() {
        String userId = getUserId();

        if (userId == null) {
            return Optional.empty();
        }

        return userRepository.findById(userId);
    }

    public User getUserAuthentatedOrThrows() {
        return this.getUserAuthenticated().orElseThrow(() -> new UserNotFoundException(getUserId()));
    }

    public boolean hasAdminRole() {
        return hasAuthority("ADMIN");
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasAnyAuthority(String... authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> Arrays.asList(authorityName).contains(authority.getAuthority()));
    }

    public boolean canUpdateAnyPassword() {
        return this.hasAdminRole();
    }
}
