package br.com.bonnafood.app.recipes.domain.service.impl;

import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.bonnafood.app.recipes.domain.repository.RecipeSpecification.usingFilter;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repository;

    @Override
    public Page<Recipe> search(RecipeFilter filter, Pageable page) {
        return repository.findAll(usingFilter(filter), page);
    }
}
