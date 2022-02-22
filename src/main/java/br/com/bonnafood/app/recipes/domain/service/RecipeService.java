package br.com.bonnafood.app.recipes.domain.service;

import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {
    Page<Recipe> search(RecipeFilter filter, Pageable page);
}
