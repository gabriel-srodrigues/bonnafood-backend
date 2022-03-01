package br.com.bonnafood.app.recipes.domain.repository;

import br.com.bonnafood.app.common.jpa.CustomJpaRepository;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RecipeRepository extends CustomJpaRepository<Recipe, String>, JpaSpecificationExecutor<Recipe> {
    boolean isRecipeOwner(String recipeId, String userId);

    Optional<Recipe> findByIdAndFetchTags(String id);

}
