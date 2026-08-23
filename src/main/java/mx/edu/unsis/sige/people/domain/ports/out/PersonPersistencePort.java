package mx.edu.unsis.sige.people.domain.ports.out;

import mx.edu.unsis.sige.people.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonPersistencePort {

    Person save(Person person);

    Optional<Person> findById(UUID id);

    Optional<Person> findByCurp(String curp);

    boolean existsByCurp(String curp);
}