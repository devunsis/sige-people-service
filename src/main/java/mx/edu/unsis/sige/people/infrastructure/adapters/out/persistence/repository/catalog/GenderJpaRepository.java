package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.catalog;

import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.catalog.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenderJpaRepository extends JpaRepository<GenderEntity, UUID> {

    Optional<GenderEntity> findByCode(String code);

    boolean existsByCode(String code);

    List<GenderEntity> findAllByIsActiveTrue();
}
