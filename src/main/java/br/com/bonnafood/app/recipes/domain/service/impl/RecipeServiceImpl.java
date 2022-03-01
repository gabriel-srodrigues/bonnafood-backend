package br.com.bonnafood.app.recipes.domain.service.impl;

import br.com.bonnafood.app.recipes.domain.exception.RecipeNotFoundException;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static br.com.bonnafood.app.recipes.domain.repository.RecipeSpecification.usingFilter;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repository;

    @Override
    public Page<Recipe> search(RecipeFilter filter, Pageable page) {
        return repository.findAll(usingFilter(filter), page);
    }

    @Override
    public Recipe findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Override
    public Recipe save(Recipe currentRecipe) {
        return currentRecipe.isNew() ?  this.create(currentRecipe) : this.update(currentRecipe);
    }

    @Transactional
     Recipe update(Recipe recipe) {
        recipe = repository.save(recipe);
        repository.flush();

        return recipe;
    }

    Recipe create(Recipe recipe) {
        recipe = repository.save(recipe);
        repository.flush();

        return recipe;
    }
}
