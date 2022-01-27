package br.com.bonnafood.app.common.domain.model;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
