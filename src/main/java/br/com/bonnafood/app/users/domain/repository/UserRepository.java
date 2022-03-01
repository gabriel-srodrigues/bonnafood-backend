package br.com.bonnafood.app.users.domain.repository;


import br.com.bonnafood.app.common.jpa.CustomJpaRepository;
import br.com.bonnafood.app.users.domain.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("from User u join fetch u.groups g left join fetch g.permissions where u.email = :email")
    Optional<User> findByEmail(String email);

    @Override
    @Query("from User u left join fetch u.groups g left join fetch g.permissions where u.id = :id")
    Optional<User> findById(String id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndDifferentUserId(String email, String userId);

}
