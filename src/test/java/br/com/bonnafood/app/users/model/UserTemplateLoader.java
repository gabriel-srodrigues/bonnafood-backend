package br.com.bonnafood.app.users.model;

import br.com.bonnafood.app.users.builders.UserBuilder;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnasys.core.template.Template;
import br.com.bonnasys.core.template.TemplateLoader;

import java.util.HashMap;
import java.util.Map;

public class UserTemplateLoader implements TemplateLoader<User> {
    private final Map<UserTemplate, User> templates;

    public enum UserTemplate implements Template {
        DEFAULT_USER, USER_WITH_INVALID_PHONE, USER_WITH_VALID_PHONE;
    }

    public UserTemplateLoader() {
        templates = new HashMap<>();
        templates.put(UserTemplate.DEFAULT_USER, getDefaultUser());
        templates.put(UserTemplate.USER_WITH_INVALID_PHONE, getUserWithInvalidPhone());
        templates.put(UserTemplate.USER_WITH_VALID_PHONE, getUserWithValidPhone());
    }

    @Override
    public User get(Template template) {
        return templates.get(template);
    }

    private static User getDefaultUser() {
        return new User();
    }

    private static User getUserWithInvalidPhone() {
        return new UserBuilder().anyUser().withPassword("Test123@").withPhone("000000000").build();
    }

    private static User getUserWithValidPhone() {
        return  new UserBuilder().anyUser().withPassword("Test123@21d").withPhone("(99) 99999-9999").build();
    }
}
