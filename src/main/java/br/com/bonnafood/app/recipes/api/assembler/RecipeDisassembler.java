package br.com.bonnafood.app.recipes.api.assembler;

import br.com.bonnafood.app.recipes.api.model.RecipeRequest;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record RecipeDisassembler(ModelMapper modelMapper) {

    public Recipe toDomainObject(RecipeRequest recipeRequest) {
        return modelMapper.map(recipeRequest, Recipe.class);
    }
}
