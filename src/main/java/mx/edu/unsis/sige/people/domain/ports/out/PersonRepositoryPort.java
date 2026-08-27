package mx.edu.unsis.sige.people.domain.ports.out;

import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryPort {
    Person save(Person person);
    Optional<Person> findById(UUID id);
    Optional<Person> findByCurp(Curp curp);
    boolean existsByCurp(Curp curp);
}