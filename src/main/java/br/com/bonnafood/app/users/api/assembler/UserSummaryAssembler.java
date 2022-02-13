package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.users.api.controller.UserController;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Relation("users")
@Component
public class UserSummaryAssembler extends RepresentationModelAssemblerSupport<BonnaUser, UserSummaryResponse> {
    private final BonnafoodLinks bonnafoodLinks;
    private final ModelMapper modelMapper;

    @Autowired
    public UserSummaryAssembler(ModelMapper modelMapper, BonnafoodLinks bonnafoodLinks) {
        super(UserController.class, UserSummaryResponse.class);
        this.bonnafoodLinks = bonnafoodLinks;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSummaryResponse toModel( BonnaUser user) {
        UserSummaryResponse userSummaryResponse = createModelWithId(user.getId(), user);
        modelMapper.map(user, userSummaryResponse);

        userSummaryResponse.add(bonnafoodLinks.linkToUsers());

        return userSummaryResponse;
    }

}
