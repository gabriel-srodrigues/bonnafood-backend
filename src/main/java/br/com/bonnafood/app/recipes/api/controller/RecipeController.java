package br.com.bonnafood.app.recipes.api.controller;

import br.com.bonnafood.app.recipes.api.assembler.RecipeSummaryAssembler;
import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.api.openapi.RecipeControllerOpenApi;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("recipes")
public class RecipeController implements RecipeControllerOpenApi {
    private final RecipeService service;
    private final RecipeSummaryAssembler summaryAssembler;
    private final PagedResourcesAssembler<Recipe> pagedResourcesAssembler;

    @Override
    @GetMapping
    public PagedModel<RecipeSummaryResponse> search(RecipeFilter recipeFilter, Pageable page) {
        Page<Recipe> pageRecipe = service.search(recipeFilter, page);
        return pagedResourcesAssembler.toModel(pageRecipe, summaryAssembler);
    }
}
