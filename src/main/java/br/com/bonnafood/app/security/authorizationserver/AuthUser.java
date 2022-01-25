package br.com.bonnafood.app.security.authorizationserver;

import br.com.bonnafood.app.users.domain.model.BonnaUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {
    private static final long serialVersionUID = 1L;

    private final String userId;
    private final String fullName;

    public AuthUser(BonnaUser bonnaUser, Collection<? extends GrantedAuthority> authorities) {
        super(bonnaUser.getEmail(), bonnaUser.getPassword(), authorities);

        this.userId = bonnaUser.getId();
        this.fullName = bonnaUser.getName();
    }

}
