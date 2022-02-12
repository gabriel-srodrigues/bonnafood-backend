package br.com.bonnafood.app.users.api.assembler;

import br.com.bonnafood.app.users.api.controller.UserController;
import br.com.bonnafood.app.users.api.model.UserSummaryResponse;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserSummaryAssembler extends RepresentationModelAssemblerSupport<BonnaUser, UserSummaryResponse> {

    private final ModelMapper modelMapper;

    @Autowired
    public UserSummaryAssembler(ModelMapper modelMapper) {
        super(UserController.class, UserSummaryResponse.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSummaryResponse toModel(BonnaUser user) {
        UserSummaryResponse userSummaryResponse = modelMapper.map(user, UserSummaryResponse.class);
        userSummaryResponse.add(linkTo(UserController.class).withRel(IanaLinkRelations.COLLECTION));
        userSummaryResponse.add(linkTo(methodOn(UserController.class).findById(userSummaryResponse.getId())).withSelfRel());
        return userSummaryResponse;
    }

    public List<UserSummaryResponse> toCollectionModel(List<BonnaUser> users) {
        return users.stream().map(this::toModel).toList();
    }
}
