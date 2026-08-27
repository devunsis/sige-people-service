package mx.edu.unsis.sige.people.domain.port.in;

import mx.edu.unsis.sige.people.domain.model.Person;

public interface CreateStaffPersonUseCase {

    Person createStaffPerson(Person person);
}