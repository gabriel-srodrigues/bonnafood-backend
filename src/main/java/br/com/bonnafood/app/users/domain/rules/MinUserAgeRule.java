package br.com.bonnafood.app.users.domain.rules;

import br.com.bonnafood.app.common.rules.CompositeRule;
import br.com.bonnafood.app.users.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class MinUserAgeRule extends CompositeRule<User> {
    @Override
    public boolean isSatisfieldBy(User user) {
        return user.getAge() > 18;
    }
}
