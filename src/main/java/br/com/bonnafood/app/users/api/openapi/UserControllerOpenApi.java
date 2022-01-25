package br.com.bonnafood.app.users.api.openapi;

import br.com.bonnafood.app.users.api.model.UpdatePasswordRequest;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuários")
public interface UserControllerOpenApi {
    @Operation(operationId = "search_users")
    ResponseEntity<Page<UserSummaryResponse>> search(Pageable page, UserFilter userFilter, Sort sort);
    @Operation(operationId = "create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created")
    })
    ResponseEntity<Void> create(UserRequest userRequest);
    @Operation(operationId = "find_by_id")
    ResponseEntity<UserDetailedResponse> findById(String id);
    @Operation(operationId = "update")
    ResponseEntity<UserDetailedResponse> update(String id, UserRequest userRequest);
    @Operation(operationId = "update_password")
    ResponseEntity<Void> updatePassword(String id, UpdatePasswordRequest updatePasswordRequest);
}
