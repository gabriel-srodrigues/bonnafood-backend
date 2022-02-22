package br.com.bonnafood.app.common.api.controller;

import br.com.bonnafood.app.common.BonnafoodLinks;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Root")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
    private final BonnafoodLinks bonnafoodLinks;

    @GetMapping
    public RootEntryPointModel root() {
     RootEntryPointModel root = new RootEntryPointModel();

     root.add(bonnafoodLinks.linkToUsers());
     root.add(bonnafoodLinks.linkToRecipes());

     return root;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
