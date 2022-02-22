package br.com.bonnafood.app.recipes.api.controller;

import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.api.openapi.RecipeControllerOpenApi;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
public class RecipeController implements RecipeControllerOpenApi {


    @Override
    public PagedModel<RecipeSummaryResponse> search(RecipeFilter recipeFilter, Pageable page) {
        return null;
    }
}
