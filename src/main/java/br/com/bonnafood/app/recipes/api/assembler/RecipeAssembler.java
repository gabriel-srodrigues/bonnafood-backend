package br.com.bonnafood.app.recipes.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.recipes.api.controller.RecipeController;
import br.com.bonnafood.app.recipes.api.model.RecipeResponse;
import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RecipeAssembler extends RepresentationModelAssemblerSupport<Recipe, RecipeResponse> {
    private final ModelMapper modelMapper;
    private final BonnafoodLinks bonnafoodLinks;

    public RecipeAssembler(ModelMapper modelMapper, BonnafoodLinks bonnafoodLinks) {
        super(RecipeController.class, RecipeResponse.class);
        this.modelMapper = modelMapper;
        this.bonnafoodLinks = bonnafoodLinks;
    }

    @Override
    public RecipeResponse toModel(Recipe recipe) {
        RecipeResponse recipeResponse = createModelWithId(recipe.getId(), recipe);
        modelMapper.map(recipe, recipeResponse);
        recipeResponse.add(this.bonnafoodLinks.linkToRecipes());
        return recipeResponse;
    }
}
