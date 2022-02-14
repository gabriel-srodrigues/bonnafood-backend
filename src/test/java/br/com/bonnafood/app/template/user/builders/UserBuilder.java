package br.com.bonnafood.app.template.user.builders;

import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserBuilder {
    private static final String DEFAULT_USER_ID = "9073da9c-1c8e-4308-a8cb-5197356f1eaa";
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private User user;

    public UserBuilder anyUser() {
        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        initializeDefaultValues(builder);
        return builder;
    }

    private static void initializeDefaultValues(UserBuilder userBuilder) {
        userBuilder.user.setEmail("some@Bonnafood.com.br");
        userBuilder.user.setRole(EnumRoleType.USER);
        userBuilder.user.setName("Some some");
        userBuilder.user.setActive(true);
    }

    public UserBuilder withId() {
        user.setId(DEFAULT_USER_ID);
        return this;
    }

    public UserBuilder withPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder withEncodedPassword(String password) {
        user.setPassword(ENCODER.encode(password));
        return this;
    }

    public UserBuilder withPhone(String phone) {
        user.setPhone(phone);
        return this;
    }

    public User build() {
        return user;
    }

}
