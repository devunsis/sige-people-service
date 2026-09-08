package mx.edu.unsis.sige.people.infrastructure.adapters.in.web.mapper;

import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.ContactTypeResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.DocumentTypeResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.GenderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convierte los modelos de dominio de catálogo en los DTOs de respuesta
 * expuestos por los controladores REST.
 */
@Component
public class CatalogResponseMapper {

    public ContactTypeResponse toResponse(ContactType contactType) {
        return new ContactTypeResponse(
                contactType.getId(),
                contactType.getCode(),
                contactType.getName(),
                contactType.getDescription(),
                contactType.isActive());
    }

    public GenderResponse toResponse(Gender gender) {
        return new GenderResponse(
                gender.getId(),
                gender.getCode(),
                gender.getName(),
                gender.getDescription(),
                gender.isActive());
    }

    public DocumentTypeResponse toResponse(DocumentType documentType) {
        return new DocumentTypeResponse(
                documentType.getId(),
                documentType.getCode(),
                documentType.getName(),
                documentType.getDescription(),
                documentType.isActive());
    }

    public List<ContactTypeResponse> toContactTypeResponses(List<ContactType> contactTypes) {
        return contactTypes.stream().map(this::toResponse).toList();
    }

    public List<GenderResponse> toGenderResponses(List<Gender> genders) {
        return genders.stream().map(this::toResponse).toList();
    }

    public List<DocumentTypeResponse> toDocumentTypeResponses(List<DocumentType> documentTypes) {
        return documentTypes.stream().map(this::toResponse).toList();
    }
}
