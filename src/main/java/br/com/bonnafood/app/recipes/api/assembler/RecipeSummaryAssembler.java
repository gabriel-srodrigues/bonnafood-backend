package br.com.bonnafood.app.recipes.api.assembler;

import br.com.bonnafood.app.common.BonnafoodLinks;
import br.com.bonnafood.app.recipes.api.controller.RecipeController;
import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RecipeSummaryAssembler extends RepresentationModelAssemblerSupport<Recipe, RecipeSummaryResponse> {
    private final ModelMapper modelMapper;
    private final BonnafoodLinks bonnafoodLinks;

    public RecipeSummaryAssembler(ModelMapper modelMapper, BonnafoodLinks bonnafoodLinks) {
        super(RecipeController.class, RecipeSummaryResponse.class);
        this.modelMapper = modelMapper;
        this.bonnafoodLinks = bonnafoodLinks;
    }

    @Override
    public RecipeSummaryResponse toModel(Recipe recipe) {
        RecipeSummaryResponse recipeSummaryResponse = createModelWithId(recipe.getId(), recipe);
        modelMapper.map(recipe, recipeSummaryResponse);
        recipeSummaryResponse.add(this.bonnafoodLinks.linkToRecipes());
        return recipeSummaryResponse;
    }
}
