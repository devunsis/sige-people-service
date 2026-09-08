package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.catalog;

import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.catalog.ContactTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactTypeJpaRepository extends JpaRepository<ContactTypeEntity, UUID> {

    Optional<ContactTypeEntity> findByCode(String code);

    boolean existsByCode(String code);

    List<ContactTypeEntity> findAllByIsActiveTrue();
}
