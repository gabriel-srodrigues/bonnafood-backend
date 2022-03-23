package br.com.bonnafood.app.users.domain.service.impl;

import br.com.bonnafood.app.common.rules.Rule;
import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.rules.MinUserAgeRule;
import br.com.bonnafood.app.users.domain.rules.RoleUserRule;
import br.com.bonnafood.app.users.domain.rules.UserNameRule;
import br.com.bonnafood.app.users.model.UserTemplateLoader;
import org.junit.jupiter.api.Test;

import static br.com.bonnafood.app.users.model.UserTemplateLoader.UserTemplate.USER_WITH_INVALID_AGE;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserTemplateLoader templateLoader = new UserTemplateLoader();

    @Test
    public void given_validUserName_then_returnsTrue(){
        User user = templateLoader.get(UserTemplateLoader.UserTemplate.DEFAULT_USER);
        Rule<User> userRules = new UserNameRule();
        assertTrue(userRules.isSatisfieldBy(user));

    }

    @Test
    public void given_invalidUserName_then_returnsFalse(){
        User user = templateLoader.get(UserTemplateLoader.UserTemplate.USER_WITH_INVALID_NAME);
        Rule<User> userRules = new UserNameRule();
        assertFalse(userRules.isSatisfieldBy(user));

    }

    @Test
    public void given_invalidUserAge_then_returnsFalse(){
        User user = templateLoader.get(USER_WITH_INVALID_AGE);
        Rule<User> userRules = new MinUserAgeRule();
        assertFalse(userRules.isSatisfieldBy(user));
    }

    @Test
    public void given_validUserAge_then_returnsTrue(){
        User user = templateLoader.get(UserTemplateLoader.UserTemplate.DEFAULT_USER);
        Rule<User> userRules = new MinUserAgeRule();
        assertTrue(userRules.isSatisfieldBy(user));
    }

    @Test
    public void given_validUserAgeAndRole_then_returnsTrue(){
        User user = templateLoader.get(UserTemplateLoader.UserTemplate.DEFAULT_USER);
        Rule<User> userRules = new MinUserAgeRule().and(new RoleUserRule(EnumRoleType.USER));
        assertTrue(userRules.isSatisfieldBy(user));
    }

    @Test
    public void given_invalidUserAgeAndRole_then_returnsFalse(){
        User user = templateLoader.get(USER_WITH_INVALID_AGE);
        Rule<User> userRules = new MinUserAgeRule().and(new RoleUserRule(EnumRoleType.USER));
        assertFalse(userRules.isSatisfieldBy(user));
    }

}