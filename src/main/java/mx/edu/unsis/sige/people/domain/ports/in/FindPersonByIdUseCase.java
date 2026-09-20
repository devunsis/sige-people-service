package mx.edu.unsis.sige.people.domain.ports.in;

import mx.edu.unsis.sige.people.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface FindPersonByIdUseCase {

    Optional<Person> findById(UUID id);
}