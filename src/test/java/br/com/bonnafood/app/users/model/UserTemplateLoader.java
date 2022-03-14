package br.com.bonnafood.app.users.model;

import br.com.bonnafood.app.users.builders.UserBuilder;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnasys.common.core.template.Template;
import br.com.bonnasys.common.core.template.TemplateLoader;

import java.util.HashMap;
import java.util.Map;

public class UserTemplateLoader implements TemplateLoader<User> {
    private static final String VALID_PHONE = "(99) 99999-9999";
    private static final String INVALID_PHONE = "99999999999";
    private static final String PASSWORD = "PtG$339Dasf";
    private static final String USER_NAME = "Gabriel Rodrigues";

    private final Map<UserTemplate, User> templates;

    public enum UserTemplate implements Template {
        DEFAULT_USER,
        USER_WITH_INVALID_NAME,
        USER_WITH_INVALID_PHONE,
        USER_WITH_VALID_PHONE,
        USER_WITH_INVALID_AGE;
    }

    public UserTemplateLoader() {
        templates = new HashMap<>();
        templates.put(UserTemplate.DEFAULT_USER, getDefaultUser().build());
        templates.put(UserTemplate.USER_WITH_INVALID_NAME, getUserWithInvalidName());
        templates.put(UserTemplate.USER_WITH_INVALID_PHONE, getUserWithInvalidPhone());
        templates.put(UserTemplate.USER_WITH_INVALID_AGE, getUserWithInvalidAge());
        templates.put(UserTemplate.USER_WITH_VALID_PHONE, getUserWithValidPhone());
    }

    private User getUserWithInvalidName() {
        return getDefaultUser().withUserName("piroca").build();
    }

    @Override
    public User get(Template template) {
        return templates.get(template);
    }

    private static UserBuilder getDefaultUser() {
        return new UserBuilder().anyUser().withUserName(USER_NAME).withPassword(PASSWORD).withPhone(VALID_PHONE);
    }

    private static User getUserWithInvalidPhone() {
        return getDefaultUser().withPhone(INVALID_PHONE).build();
    }

    private static User getUserWithValidPhone() {
        return  getDefaultUser().withPhone(VALID_PHONE).build();
    }

    private static User getUserWithInvalidAge() {
        return  getDefaultUser().withAge(12).withPhone(VALID_PHONE).build();
    }


}
