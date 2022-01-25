package br.com.bonnafood.app.users.domain.repository;


import br.com.bonnafood.app.common.jpa.CustomJpaRepository;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<BonnaUser, String>, JpaSpecificationExecutor<BonnaUser> {
    Optional<BonnaUser> findByEmail(String email);
}
