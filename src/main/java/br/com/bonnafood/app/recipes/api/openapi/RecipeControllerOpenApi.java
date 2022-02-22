package br.com.bonnafood.app.recipes.api.openapi;

import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Tag(name = "Receitas")
public interface RecipeControllerOpenApi {

    @Operation(operationId = "search_recipes")
    PagedModel<RecipeSummaryResponse> search(RecipeFilter recipeFilter, Pageable page);
}
