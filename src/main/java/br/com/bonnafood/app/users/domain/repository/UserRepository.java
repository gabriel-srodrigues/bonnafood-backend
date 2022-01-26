package br.com.bonnafood.app.users.domain.repository;


import br.com.bonnafood.app.common.jpa.CustomJpaRepository;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<BonnaUser, String>, JpaSpecificationExecutor<BonnaUser> {
    @Query("from user u join fetch u.groups g left join fetch g.permissions where u.email = :email")
    Optional<BonnaUser> findByEmail(String email);

    @Override
    @Query("from user u join fetch u.groups g left join fetch g.permissions where u.id = :id")
    Optional<BonnaUser> findById(String id);
}
