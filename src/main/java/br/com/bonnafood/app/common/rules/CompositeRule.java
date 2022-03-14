package br.com.bonnafood.app.common.rules;

import br.com.bonnafood.app.common.rules.conditionals.AndConditionalRule;

public abstract class CompositeRule<T> implements Rule<T> {

    public CompositeRule<T> and(Rule<T> andRule) {
        return new AndConditionalRule<T>(this, andRule);
    }

    @Override
    public abstract boolean isSatisfieldBy(T t);

}

