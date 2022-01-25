package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record UserSummaryAssembler(ModelMapper modelMapper) {

    public UserSummaryResponse toModel(BonnaUser user) {
        return modelMapper.map(user, UserSummaryResponse.class);
    }

    public List<UserSummaryResponse> toCollectionModel(List<BonnaUser> users) {
        return users.stream().map(this::toModel).toList();
    }
}
