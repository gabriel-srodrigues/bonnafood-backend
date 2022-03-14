package br.com.bonnafood.app.users.domain.rules;

import br.com.bonnafood.app.common.rules.CompositeRule;
import br.com.bonnafood.app.users.domain.enums.EnumRoleType;
import br.com.bonnafood.app.users.domain.model.User;

public class RoleUserRule extends CompositeRule<User> {
    private final EnumRoleType role;

    public RoleUserRule(EnumRoleType role) {
        this.role = role;
    }

    @Override
    public boolean isSatisfieldBy(User user) {
        return user.getRole() != null && user.getRole().getOrder() >=  role.getOrder();
    }
}
