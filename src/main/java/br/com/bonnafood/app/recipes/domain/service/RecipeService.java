package br.com.bonnafood.app.recipes.domain.service;

import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RecipeService {
    Page<Recipe> search(RecipeFilter filter, Pageable page);

    Recipe create(Recipe recipe);

    Recipe findById(String id);

    void updateRecipe(String recipeId, Map<String, Object> fields);
}