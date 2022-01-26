package br.com.bonnafood.app.users.domain.service;

import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCrudService {
    BonnaUser findByIdOrThrows(String id);

    BonnaUser save(BonnaUser user);

    void updatePassword(String id, String oldPassword, String newPassword);

    Page<BonnaUser> search(UserFilter userFilter, Pageable page);
}
