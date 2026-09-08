package mx.edu.unsis.sige.people.domain.ports.in;

import mx.edu.unsis.sige.people.domain.model.catalog.ContactType;
import mx.edu.unsis.sige.people.domain.model.catalog.DocumentType;
import mx.edu.unsis.sige.people.domain.model.catalog.Gender;

import java.util.List;

/**
 * Puerto de entrada para la consulta de los catálogos básicos del dominio Person.
 * Cada método devuelve únicamente los elementos activos del catálogo.
 */
public interface FindCatalogsUseCase {

    List<ContactType> findContactTypes();

    List<Gender> findGenders();

    List<DocumentType> findDocumentTypes();
}
