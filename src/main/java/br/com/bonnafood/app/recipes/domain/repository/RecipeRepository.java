package br.com.bonnafood.app.recipes.domain.repository;

import br.com.bonnafood.app.common.jpa.CustomJpaRepository;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

public interface RecipeRepository extends CustomJpaRepository<Recipe, String>, Specification<Recipe> {
}
