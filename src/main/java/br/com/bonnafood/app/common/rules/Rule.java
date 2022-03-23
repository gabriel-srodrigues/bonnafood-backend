package br.com.bonnafood.app.common.rules;

@FunctionalInterface
public interface Rule<T> {
    boolean isSatisfieldBy(T model);
}
