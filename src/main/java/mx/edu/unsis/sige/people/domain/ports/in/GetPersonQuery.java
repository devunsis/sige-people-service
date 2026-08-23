package mx.edu.unsis.sige.people.domain.ports.in;

import mx.edu.unsis.sige.people.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface GetPersonQuery {

    Optional<Person> getById(UUID id);

    Optional<Person> getByCurp(String curp);

    boolean existsByCurp(String curp);
}