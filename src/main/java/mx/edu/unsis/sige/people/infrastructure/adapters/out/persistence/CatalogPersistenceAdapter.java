package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence;

import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;
import mx.edu.unsis.sige.people.domain.ports.out.CatalogRepositoryPort;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.mapper.CatalogPersistenceMapper;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.catalog.ContactTypeJpaRepository;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.catalog.DocumentTypeJpaRepository;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.catalog.GenderJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Adaptador de salida que resuelve el {@link CatalogRepositoryPort} usando los
 * repositorios Spring Data JPA de los catálogos y el mapper hacia dominio.
 */
@Component
@RequiredArgsConstructor
public class CatalogPersistenceAdapter implements CatalogRepositoryPort {

    private final ContactTypeJpaRepository contactTypeJpaRepository;
    private final GenderJpaRepository genderJpaRepository;
    private final DocumentTypeJpaRepository documentTypeJpaRepository;
    private final CatalogPersistenceMapper catalogPersistenceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ContactType> findActiveContactTypes() {
        return contactTypeJpaRepository.findAllByIsActiveTrue().stream()
                .map(catalogPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gender> findActiveGenders() {
        return genderJpaRepository.findAllByIsActiveTrue().stream()
                .map(catalogPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentType> findActiveDocumentTypes() {
        return documentTypeJpaRepository.findAllByIsActiveTrue().stream()
                .map(catalogPersistenceMapper::toDomain)
                .toList();
    }
}
