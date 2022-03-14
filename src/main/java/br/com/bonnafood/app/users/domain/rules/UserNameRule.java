package br.com.bonnafood.app.users.domain.rules;

import br.com.bonnafood.app.common.rules.CompositeRule;
import br.com.bonnafood.app.users.domain.model.User;

public class UserNameRule extends CompositeRule<User> {

    @Override
    public boolean isSatisfieldBy(User user) {
        String username = user.getName();
        return username != null && !username.contains("piroca");
    }
}
