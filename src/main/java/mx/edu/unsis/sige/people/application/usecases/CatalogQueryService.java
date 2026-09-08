package mx.edu.unsis.sige.people.application.usecases;

import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;
import mx.edu.unsis.sige.people.domain.ports.in.FindCatalogsUseCase;
import mx.edu.unsis.sige.people.domain.ports.out.CatalogRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Orquestación de la lectura de catálogos básicos. Delega en el puerto de
 * salida y mantiene el controlador desacoplado de la persistencia.
 */
@Service
@RequiredArgsConstructor
public class CatalogQueryService implements FindCatalogsUseCase {

    private final CatalogRepositoryPort catalogRepositoryPort;

    @Override
    public List<ContactType> findContactTypes() {
        return catalogRepositoryPort.findActiveContactTypes();
    }

    @Override
    public List<Gender> findGenders() {
        return catalogRepositoryPort.findActiveGenders();
    }

    @Override
    public List<DocumentType> findDocumentTypes() {
        return catalogRepositoryPort.findActiveDocumentTypes();
    }
}
