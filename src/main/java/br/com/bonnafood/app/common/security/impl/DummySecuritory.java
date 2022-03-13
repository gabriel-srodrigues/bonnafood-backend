package br.com.bonnafood.app.common.security.impl;

import br.com.bonnafood.app.common.security.BonnafoodSecurity;
import br.com.bonnafood.app.common.security.config.SecurityProperties;
import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("bonnafoodSecurity")
@ConditionalOnProperty(name = "bonnafood.security.disabled", havingValue = "true")
public class DummySecuritory extends BonnafoodSecurity {
private final SecurityProperties securityProperties;

    public DummySecuritory(UserRepository userRepository, RecipeRepository recipeRepository, SecurityProperties properties) {
        super(userRepository, recipeRepository);
        securityProperties = properties;
    }

    @Override
    public String getUserId() {
        return securityProperties.getDefaultUserIdIfDisabled();
    }

}
