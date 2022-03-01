package br.com.bonnafood.app.recipes.domain.repository;

import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RecipeSpecification {

    private RecipeSpecification() {
    }

    public static Specification<Recipe> usingFilter(RecipeFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getAuthor() != null && !filter.getAuthor().isBlank()) {
                var searchTerm = filter.getAuthor() + "%";
                predicates.add(criteriaBuilder.like(root.get("createdBy"), searchTerm));
            }
            if (filter.getTag() != null && !filter.getTag().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("name"), filter.getTag()));
            }
            if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
                var searchTerm = "%" + filter.getTitle() + "%";
                predicates.add(criteriaBuilder.like(root.get("title"), searchTerm));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }
}
