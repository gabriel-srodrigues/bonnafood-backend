package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.users.api.model.UserPasswordRequest;
import br.com.bonnafood.app.users.api.model.UserRequest;
import br.com.bonnafood.app.users.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record UserDisassembler (ModelMapper modelMapper) {

    public User toDomainObject(UserRequest user) {
        return modelMapper.map(user, User.class);
    }

    public User toDomainObject(UserPasswordRequest user) {
        return modelMapper.map(user, User.class);
    }

    public List<User> toCollectionDomainObject(List<UserRequest> users) {
        return users.stream().map(this::toDomainObject).toList();
    }
}