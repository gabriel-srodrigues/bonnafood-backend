package br.com.bonnafood.app.recipes.api.openapi;

import br.com.bonnafood.app.recipes.api.model.RecipeRequest;
import br.com.bonnafood.app.recipes.api.model.RecipeResponse;
import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.security.CheckSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(name = "Receitas")
public interface RecipeControllerOpenApi {

    @Operation(operationId = "search_recipes")
    PagedModel<RecipeSummaryResponse> search(RecipeFilter recipeFilter, Pageable page);

    @CheckSecurity.Recipes.CanCreate
    @Operation(operationId = "create_recipe")
    ResponseEntity<Void> create(RecipeRequest request);

    @CheckSecurity.Recipes.CanCreate
    ResponseEntity<RecipeResponse> getRecipeById(String id);

    @CheckSecurity.Recipes.CanEdit
    ResponseEntity<Void> update(String recipeId, Map<String, Object> fields);

    @CheckSecurity.Recipes.CanDelete
    ResponseEntity<Void> delete(String recipeId);
}
