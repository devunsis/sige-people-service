package mx.edu.unsis.sige.auth.adapter.out.persistence.repository;

import mx.edu.unsis.sige.auth.adapter.out.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}