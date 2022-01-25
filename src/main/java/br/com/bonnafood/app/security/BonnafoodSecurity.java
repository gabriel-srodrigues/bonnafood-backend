package br.com.bonnafood.app.security;

import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@AllArgsConstructor
public abstract class BonnafoodSecurity {

    public static final String SCOPE_ALL_WRITE = "SCOPE_all:write";
    public static final String SCOPE_ALL_READ = "SCOPE_all:read";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EDITOR = "EDITOR";
    public static final String ROLE_USER = "USER";

    private final UserRepository userRepository;

    public abstract String getUserId();


    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Optional<BonnaUser> getAuthenticatedUser() {
        String userId = getUserId();

        if (userId == null) {
            return Optional.empty();
        }

        return userRepository.findById(userId);
    }

}
