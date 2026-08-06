package mx.edu.unsis.sige.auth.adapter.out.persistence.repository;

import mx.edu.unsis.sige.auth.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query("""
                SELECT a FROM AccountEntity a
                LEFT JOIN FETCH a.roles
                WHERE a.username = :username
            """)
    Optional<AccountEntity> findByUsername(@Param("username") String username);
}