package br.com.bonnafood.app.common.rules.conditionals;

import br.com.bonnafood.app.common.rules.CompositeRule;
import br.com.bonnafood.app.common.rules.Rule;

public class AndConditionalRule<T> extends CompositeRule<T> {
    private final Rule<T> leftRule;
    private final Rule<T> rightRule;

    public AndConditionalRule(Rule<T> leftRule, Rule<T> rightRule) {
        this.leftRule = leftRule;
        this.rightRule = rightRule;
    }
    @Override
    public boolean isSatisfieldBy(T model) {
        return leftRule.isSatisfieldBy(model) && rightRule.isSatisfieldBy(model);
    }
}
