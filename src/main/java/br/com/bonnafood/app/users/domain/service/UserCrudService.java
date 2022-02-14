package br.com.bonnafood.app.users.domain.service;

import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCrudService {
    User findByIdOrThrows(String id);

    User save(User user);

    void updatePassword(String id, String oldPassword, String newPassword);

    Page<User> search(UserFilter userFilter, Pageable page);
}
