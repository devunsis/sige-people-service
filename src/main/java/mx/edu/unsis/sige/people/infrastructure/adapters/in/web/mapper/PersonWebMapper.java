package mx.edu.unsis.sige.people.infrastructure.adapters.in.web.mapper;

import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.CreatePersonRequest;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonWebMapper {

    public Person toDomain(CreatePersonRequest request) {
        if (request == null)
            return null;

        return Person.builder()
                .curp(request.getCurp() != null ? new Curp(request.getCurp()) : null)
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .firstSurname(request.getFirstSurname())
                .secondSurname(request.getSecondSurname())
                .birthDate(request.getBirthDate())
                .build();
    }

    public PersonResponse toResponse(Person person) {
        if (person == null)
            return null;

        return PersonResponse.builder()
                .id(person.getId())
                .curp(person.getCurp() != null ? person.getCurp().getValue() : null)
                .firstName(person.getFirstName())
                .secondName(person.getSecondName())
                .firstSurname(person.getFirstSurname())
                .secondSurname(person.getSecondSurname())
                .birthDate(person.getBirthDate())
                .createdAt(person.getCreatedAt())
                .updatedAt(person.getUpdatedAt())
                .build();
    }
}
