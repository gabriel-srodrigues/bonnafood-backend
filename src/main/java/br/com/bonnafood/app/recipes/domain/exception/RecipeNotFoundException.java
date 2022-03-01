package br.com.bonnafood.app.recipes.domain.exception;

import br.com.bonnafood.app.common.domain.model.EntityNotFoundException;

public class RecipeNotFoundException extends EntityNotFoundException {

    public RecipeNotFoundException(String id) {
        super("Recipe with id '%s' not found.".formatted(id));
    }
}
