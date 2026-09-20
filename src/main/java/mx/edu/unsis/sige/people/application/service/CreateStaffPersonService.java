package mx.edu.unsis.sige.people.application.service;

import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.ports.in.CreateStaffPersonUseCase;
import mx.edu.unsis.sige.people.domain.ports.out.OutboxEventPort;
import mx.edu.unsis.sige.people.domain.ports.out.PersonRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStaffPersonService implements CreateStaffPersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;
    private final OutboxEventPort outboxEventPort;

    @Override
    @Transactional
    public Person createStaffPerson(Person person) {
        // 1. Buscar si la persona física ya existe (ej. fue o es estudiante) o guardar
        // la nueva
        Person savedPerson = personRepositoryPort.findByCurp(person.getCurp())
                .orElseGet(() -> personRepositoryPort.save(person));

        // 2. Transactional Outbox Pattern: registrar evento
        outboxEventPort.saveEvent(
                "STAFF_PERSON",
                savedPerson.getId(),
                "STAFF_PERSON_CREATED",
                savedPerson);

        return savedPerson;
    }
}