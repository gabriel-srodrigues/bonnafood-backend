package br.com.bonnafood.app.template.user.builders;

import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.time.OffsetDateTime;
import java.util.Date;

public class UserBuilder {
    private static final String DEFAULT_USER_ID = "9073da9c-1c8e-4308-a8cb-5197356f1eaa";
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private BonnaUser user;

    public UserBuilder anyBonnaUser() {
        UserBuilder builder = new UserBuilder();
        builder.user = new BonnaUser();
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

    public BonnaUser build() {
        return user;
    }

}
