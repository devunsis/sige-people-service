package mx.edu.unsis.sige.people.domain.port.in;

import mx.edu.unsis.sige.people.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface FindPersonByIdUseCase {

    Optional<Person> findById(UUID id);
}