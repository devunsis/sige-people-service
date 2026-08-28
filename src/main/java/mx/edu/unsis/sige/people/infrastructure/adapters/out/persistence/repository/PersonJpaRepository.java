package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository;

import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, UUID> {
    Optional<PersonEntity> findByCurp(String curp);
    boolean existsByCurp(String curp);
}