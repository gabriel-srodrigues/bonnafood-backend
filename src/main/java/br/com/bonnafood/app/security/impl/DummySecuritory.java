package br.com.bonnafood.app.security.impl;

import br.com.bonnafood.app.security.BonnafoodSecurity;
import br.com.bonnafood.app.security.config.SecurityProperties;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("bonnafoodSecurity")
@ConditionalOnProperty(name = "alganews.security.disabled", havingValue = "true")
public class DummySecuritory extends BonnafoodSecurity {
private final SecurityProperties securityProperties;

    public DummySecuritory(UserRepository userRepository, SecurityProperties properties) {
        super(userRepository);
        securityProperties = properties;
    }

    @Override
    public String getUserId() {
        return securityProperties.getDefaultUserIdIfDisabled();
    }

}
