package br.com.bonnafood.app.common.rules;

public interface Rule<T> {
    boolean isSatisfieldBy(T model);
}
