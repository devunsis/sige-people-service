package mx.edu.unsis.sige.people.domain.ports.in;

import mx.edu.unsis.sige.people.domain.model.Person;

public interface CreateStaffPersonUseCase {

    Person createStaffPerson(Person person);
}