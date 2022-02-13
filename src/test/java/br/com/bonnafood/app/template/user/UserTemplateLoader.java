package br.com.bonnafood.app.template.user;

import br.com.bonnafood.app.template.Template;
import br.com.bonnafood.app.template.TemplateLoader;
import br.com.bonnafood.app.template.user.builders.UserBuilder;
import br.com.bonnafood.app.users.domain.model.BonnaUser;

import java.util.HashMap;
import java.util.Map;

public class UserTemplateLoader implements TemplateLoader<BonnaUser> {
    private final Map<UserTemplate, BonnaUser> templates;

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
    public BonnaUser get(Template template) {
        return templates.get(template);
    }

    private static BonnaUser getDefaultUser() {
        return new BonnaUser();
    }

    private static BonnaUser getUserWithInvalidPhone() {
        return new UserBuilder().anyBonnaUser().withPassword("Test123@").withPhone("000000000").build();
    }

    private static BonnaUser getUserWithValidPhone() {
        return  new UserBuilder().anyBonnaUser().withPassword("Test123@21d").withPhone("(99) 99999-9999").build();
    }
}
