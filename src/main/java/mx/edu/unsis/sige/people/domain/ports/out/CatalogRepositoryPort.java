package mx.edu.unsis.sige.people.domain.ports.out;

import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;

import java.util.List;

/**
 * Puerto de salida para leer los catálogos básicos desde el mecanismo
 * de persistencia. Devuelve solo los elementos marcados como activos.
 */
public interface CatalogRepositoryPort {

    List<ContactType> findActiveContactTypes();

    List<Gender> findActiveGenders();

    List<DocumentType> findActiveDocumentTypes();
}
