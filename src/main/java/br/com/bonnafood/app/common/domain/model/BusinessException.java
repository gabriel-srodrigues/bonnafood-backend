package br.com.bonnafood.app.common.domain.model;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
