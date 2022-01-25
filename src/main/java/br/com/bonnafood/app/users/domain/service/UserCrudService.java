package br.com.bonnafood.app.users.domain.service;

import br.com.bonnafood.app.users.domain.model.BonnaUser;

public interface UserCrudService {
    BonnaUser findByIdOrThrows(String id);
    BonnaUser save(BonnaUser user);
}
