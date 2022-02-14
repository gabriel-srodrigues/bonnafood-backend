package br.com.bonnafood.app.users.domain.repository;

import br.com.bonnafood.app.users.domain.filter.UserFilter;
import br.com.bonnafood.app.users.domain.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    private UserSpecification(){}

    public static Specification<User> usingUserFilter(UserFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(filter.getName())) {
                String searchTerm = "%" + filter.getName() + "%";
                predicates.add(criteriaBuilder.like(root.get("name"), searchTerm));
            }
            if (StringUtils.isNotBlank(filter.getEmail())) {
                String searchTerm =  filter.getEmail() + "%";
                predicates.add(criteriaBuilder.like(root.get("email"), searchTerm));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
