package br.com.bonnafood.app.recipes.domain.service.impl;

import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repository;
}
