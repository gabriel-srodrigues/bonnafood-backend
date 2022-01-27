package br.com.bonnafood.app.template;

public interface TemplateLoader<T>{
    T get(Template template);
}
