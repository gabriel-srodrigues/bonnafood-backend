package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.users.api.controller.UserController;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Relation("users")
@Component
public class UserSummaryAssembler extends RepresentationModelAssemblerSupport<User, UserSummaryResponse> {
    private final BonnafoodLinks bonnafoodLinks;
    private final ModelMapper modelMapper;

    @Autowired
    public UserSummaryAssembler(ModelMapper modelMapper, BonnafoodLinks bonnafoodLinks) {
        super(UserController.class, UserSummaryResponse.class);
        this.bonnafoodLinks = bonnafoodLinks;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSummaryResponse toModel( User user) {
        UserSummaryResponse userSummaryResponse = createModelWithId(user.getId(), user);
        modelMapper.map(user, userSummaryResponse);

        userSummaryResponse.add(bonnafoodLinks.linkToUsers());

        return userSummaryResponse;
    }

}
