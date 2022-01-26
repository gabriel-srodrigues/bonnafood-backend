package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.users.api.model.UserPasswordRequest;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record UserDisassembler (ModelMapper modelMapper) {

    public BonnaUser toDomainObject(UserRequest user) {
        return modelMapper.map(user, BonnaUser.class);
    }

    public BonnaUser toDomainObject(UserPasswordRequest user) {
        return modelMapper.map(user, BonnaUser.class);
    }

    public List<BonnaUser> toCollectionDomainObject(List<UserRequest> users) {
        return users.stream().map(this::toDomainObject).toList();
    }
}