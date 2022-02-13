package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.users.api.controller.UserController;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailedAssembler extends RepresentationModelAssemblerSupport<BonnaUser, UserDetailedResponse> {
    private final ModelMapper modelMapper;
    private final BonnafoodLinks bonnafoodLinks;

    public UserDetailedAssembler(ModelMapper mapper, BonnafoodLinks bonnafoodLinks) {
        super(UserController.class, UserDetailedResponse.class);
        this.bonnafoodLinks = bonnafoodLinks;
        this.modelMapper = mapper;
    }

    public UserDetailedResponse toModel(BonnaUser user) {
        UserDetailedResponse userDetailedResponse = createModelWithId(user.getId(), user);
        modelMapper.map(user, userDetailedResponse);

        userDetailedResponse.add(bonnafoodLinks.linkToUsers());

        return userDetailedResponse;
    }

}
