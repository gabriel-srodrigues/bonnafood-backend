package br.com.bonnafood.app.recipes.domain.repository;

import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RecipeSpecification  {

    private RecipeSpecification() {}

    public static Specification<Recipe> usingFilter(RecipeFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
