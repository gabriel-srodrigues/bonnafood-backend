package br.com.bonnafood.app.users.api.openapi;

import br.com.bonnafood.app.security.CheckSecurity;
import br.com.bonnafood.app.users.api.model.UpdatePasswordRequest;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.api.model.UserPasswordRequest;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuários")
public interface UserControllerOpenApi {

    @CheckSecurity.Admin
    @Operation(operationId = "search_users")
    ResponseEntity<PagedModel<EntityModel<UserSummaryResponse>>> search(UserFilter userFilter, Pageable page);

    @Operation(operationId = "create")
    ResponseEntity<Void> create(UserPasswordRequest userRequest);

    @Operation(operationId = "find_by_id")
    ResponseEntity<UserDetailedResponse> findById(String id);

    @Operation(operationId = "update")
    ResponseEntity<UserDetailedResponse> update(String id, UserRequest userRequest);
    
    @Operation(operationId = "update_password")
    ResponseEntity<Void> updatePassword(String id, UpdatePasswordRequest updatePasswordRequest);
}
