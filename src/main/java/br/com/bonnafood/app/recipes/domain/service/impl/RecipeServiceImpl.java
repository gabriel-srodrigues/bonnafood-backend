package br.com.bonnafood.app.recipes.domain.service.impl;

import br.com.bonnafood.app.recipes.domain.exception.RecipeNotFoundException;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

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
    public Recipe create(Recipe recipe) {
        recipe = repository.save(recipe);
        repository.flush();
        return recipe;
    }

    @Override
    public Recipe findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Override
    public void updateRecipe(String recipeId, Map<String, Object> fields) {
        Recipe currentRecipe = this.findById(recipeId);
        merge(fields, currentRecipe);
        repository.save(currentRecipe);
        repository.flush();
    }

    private void merge(Map<String, Object> fields, Recipe currentRecipe) {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipeOrigin = mapper.convertValue(fields, Recipe.class);

        fields.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Recipe.class, propertyName);
            assert field != null;
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, recipeOrigin);
            ReflectionUtils.setField(field, currentRecipe, newValue);
        });
    }

}
