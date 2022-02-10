package br.com.bonnafood.app.users.domain.exception;

import br.com.bonnafood.app.common.domain.model.BusinessException;

public class UpdatePasswordNotAllowedException extends BusinessException {

    public UpdatePasswordNotAllowedException(String message) {
        super(message);
    }
}
