package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.mapper;

import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.catalog.ContactTypeEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.catalog.DocumentTypeEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.catalog.GenderEntity;
import org.springframework.stereotype.Component;

/**
 * Convierte las entidades JPA de catálogo en modelos de dominio puros.
 * Aísla el dominio y la capa web de las clases {@code @Entity}.
 */
@Component
public class CatalogPersistenceMapper {

    public ContactType toDomain(ContactTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        return ContactType.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(Boolean.TRUE.equals(entity.getIsActive()))
                .build();
    }

    public Gender toDomain(GenderEntity entity) {
        if (entity == null) {
            return null;
        }
        return Gender.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(Boolean.TRUE.equals(entity.getIsActive()))
                .build();
    }

    public DocumentType toDomain(DocumentTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        return DocumentType.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(Boolean.TRUE.equals(entity.getIsActive()))
                .build();
    }
}
