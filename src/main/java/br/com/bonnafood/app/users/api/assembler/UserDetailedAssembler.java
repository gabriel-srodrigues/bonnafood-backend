package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record UserDetailedAssembler(ModelMapper modelMapper) {

    public UserDetailedResponse toModel(BonnaUser user) {
        return modelMapper.map(user, UserDetailedResponse.class);
    }

    public List<UserDetailedResponse> toCollectionModel(List<BonnaUser> users) {
        return users.stream().map(this::toModel).toList();
    }
}
