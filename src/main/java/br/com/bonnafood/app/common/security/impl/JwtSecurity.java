package br.com.bonnafood.app.common.security.impl;

import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import br.com.bonnafood.app.common.security.BonnafoodSecurity;
import org.springframework.stereotype.Component;

@Component("bonnafoodSecurity")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "bonnafood.security.disabled", havingValue = "false", matchIfMissing = true)
public class JwtSecurity extends BonnafoodSecurity {

    public JwtSecurity(UserRepository userRepository, RecipeRepository recipeRepository) {
        super(userRepository, recipeRepository);
    }

    @Override
    public String getUserId() {
        var jwt = getJwtToken();
        if (jwt == null) return null;
        return jwt.getClaim("user_id");
    }

    private Jwt getJwtToken() {
        if (getAuthentication() != null && getAuthentication().getPrincipal() instanceof Jwt) {
            return (Jwt) getAuthentication().getPrincipal();
        }

        return null;
    }
}
