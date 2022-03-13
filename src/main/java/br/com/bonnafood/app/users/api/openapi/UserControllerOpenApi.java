package br.com.bonnafood.app.users.api.openapi;

import br.com.bonnafood.app.common.security.CheckSecurity;
import br.com.bonnafood.app.users.api.model.UpdatePasswordRequest;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.api.model.UserPasswordRequest;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuários")
public interface UserControllerOpenApi {

    @CheckSecurity.Users.CanView
    @Operation(operationId = "search_users")
    PagedModel<UserSummaryResponse> search(UserFilter userFilter, Pageable page);

    @Operation(operationId = "create")
    @CheckSecurity.Users.CanCreate
    ResponseEntity<Void> create(UserPasswordRequest userRequest);

    @Operation(operationId = "find_by_id")
    @CheckSecurity.Users.CanSeeHimself
    ResponseEntity<UserDetailedResponse> findById(String userId);

    @Operation(operationId = "update")
    @CheckSecurity.Users.CanUpdate
    ResponseEntity<UserDetailedResponse> update(String id, UserRequest userRequest);
    
    @Operation(operationId = "update_password")
    @CheckSecurity.Users.CanUpdatePassword
    ResponseEntity<Void> updatePassword(String id, UpdatePasswordRequest updatePasswordRequest);
}
