package br.com.bonnafood.app.users.api.openapi;

import br.com.bonnafood.app.users.api.model.UpdatePasswordRequest;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface UserControllerOpenApi {
    ResponseEntity<Page<UserSummaryResponse>> search(Pageable page, UserFilter userFilter, Sort sort);
    ResponseEntity<Void> create(UserRequest userRequest);
    ResponseEntity<UserDetailedResponse> findById(String id);
    ResponseEntity<UserDetailedResponse> update(String id, UserRequest userRequest);
    ResponseEntity<Void> updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
