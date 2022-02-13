package br.com.bonnafood.app.common;

import br.com.bonnafood.app.users.api.controller.UserController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BonnafoodLinks {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public static final TemplateVariables USER_FILTER_VARIABLES = new TemplateVariables(
            new TemplateVariable("name", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("email", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToUsers() {
        String usersUrl = WebMvcLinkBuilder.linkTo(UserController.class).toUri().toString();
        return Link.of(UriTemplate.of(usersUrl, PAGINATION_VARIABLES.concat(USER_FILTER_VARIABLES)), IanaLinkRelations.COLLECTION);
    }

}
