package br.com.bonnafood.app.users.api.controller;

import br.com.bonnafood.app.users.api.assembler.UserDetailedAssembler;
import br.com.bonnafood.app.users.api.assembler.UserDisassembler;
import br.com.bonnafood.app.users.api.assembler.UserSummaryAssembler;
import br.com.bonnafood.app.users.api.model.UpdatePasswordRequest;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.api.openapi.UserControllerOpenApi;
import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import br.com.bonnafood.app.users.domain.service.UserOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("v1/users")
public class UserController implements UserControllerOpenApi {
    private final UserCrudService userCrudService;
    private final UserOnboardingService userOnboardingService;

    private final UserSummaryAssembler userSummaryAssembler;
    private final UserDetailedAssembler userDetailedAssembler;
    private final UserDisassembler disassembler;


    @Override
    @GetMapping
    public ResponseEntity<Page<UserSummaryResponse>> search(Pageable page, UserFilter userFilter, Sort sort) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid UserRequest userRequest) {
        BonnaUser user = disassembler.toDomainObject(userRequest);
        user = userCrudService.save(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri()
        ).build();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<UserDetailedResponse> findById(@PathVariable String id) {
        UserDetailedResponse response = userDetailedAssembler.toModel(userCrudService.findByIdOrThrows(id));
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<UserDetailedResponse> update(@PathVariable String id, @Valid  @RequestBody UserRequest userRequest) {
        return null;
    }

    @Override
    @PostMapping("{id}/update-password")
    public ResponseEntity<Void> updatePassword(@PathVariable String id, @Valid  @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return null;
    }
}
