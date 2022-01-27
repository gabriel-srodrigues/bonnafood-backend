package br.com.bonnafood.app.users.domain.exception;

import br.com.bonnafood.app.common.domain.model.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String id) {
        super("Usuário com id '%s' não encontrado.".formatted(id));
    }
}
