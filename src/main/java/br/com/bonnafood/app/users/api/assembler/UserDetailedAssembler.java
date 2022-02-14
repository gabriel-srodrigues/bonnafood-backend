package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.users.api.controller.UserController;
import br.com.bonnafood.app.users.api.model.UserDetailedResponse;
import br.com.bonnafood.app.users.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserDetailedAssembler extends RepresentationModelAssemblerSupport<User, UserDetailedResponse> {
    private final ModelMapper modelMapper;
    private final BonnafoodLinks bonnafoodLinks;

    public UserDetailedAssembler(ModelMapper mapper, BonnafoodLinks bonnafoodLinks) {
        super(UserController.class, UserDetailedResponse.class);
        this.bonnafoodLinks = bonnafoodLinks;
        this.modelMapper = mapper;
    }

    public UserDetailedResponse toModel(User user) {
        UserDetailedResponse userDetailedResponse = createModelWithId(user.getId(), user);
        modelMapper.map(user, userDetailedResponse);

        userDetailedResponse.add(bonnafoodLinks.linkToUsers());

        return userDetailedResponse;
    }

}
