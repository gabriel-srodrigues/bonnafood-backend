package br.com.bonnafood.app.template.user;

import br.com.bonnafood.app.template.Template;
import br.com.bonnafood.app.template.TemplateLoader;
import br.com.bonnafood.app.users.domain.model.BonnaUser;

import java.util.HashMap;
import java.util.Map;

public class UserTemplateLoader implements TemplateLoader<BonnaUser> {
    private final Map<UserTemplate, BonnaUser> templates;

    public enum UserTemplate implements Template {
        DEFAULT_USER;
    }

    public UserTemplateLoader() {
        templates = new HashMap<>();
        templates.put(UserTemplate.DEFAULT_USER, getDefaultUser());
    }

    @Override
    public BonnaUser get(Template template) {
        return templates.get(template);
    }

    private BonnaUser getDefaultUser() {
        return new BonnaUser();
    }
}
