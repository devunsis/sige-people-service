package mx.edu.unsis.sige.people.application.service;

import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import mx.edu.unsis.sige.people.domain.ports.out.OutboxEventPort;
import mx.edu.unsis.sige.people.domain.ports.out.PersonRepositoryPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateStaffPersonServiceTest {

    @Mock
    private PersonRepositoryPort personRepositoryPort;

    @Mock
    private OutboxEventPort outboxEventPort;

    @InjectMocks
    private CreateStaffPersonService createStaffPersonService;

    private Person testPerson;
    private Curp testCurp;
    private UUID personId;

    @BeforeEach
    void setUp() {
        personId = UUID.randomUUID();
        // Usamos una CURP sintácticamente válida de 18 caracteres
        testCurp = new Curp("GARI950515HOCRRM01");

        testPerson = Person.builder()
                .id(personId)
                .curp(testCurp)
                .firstName("Irving Efren")
                .firstSurname("García")
                .secondSurname("Ramos")
                .build();
    }

    @Test
    @DisplayName("Debe guardar nueva persona y emitir evento outbox cuando la CURP NO existe")
    void shouldCreateNewPersonAndSaveOutboxEvent() {
        // Arrange
        when(personRepositoryPort.findByCurp(testPerson.getCurp())).thenReturn(Optional.empty());
        when(personRepositoryPort.save(any(Person.class))).thenReturn(testPerson);

        // Act
        Person result = createStaffPersonService.createStaffPerson(testPerson);

        // Assert
        assertNotNull(result);
        assertEquals(personId, result.getId());

        // Verificaciones
        verify(personRepositoryPort, times(1)).findByCurp(testPerson.getCurp());
        verify(personRepositoryPort, times(1)).save(testPerson);
        verify(outboxEventPort, times(1)).saveEvent(
                eq("STAFF_PERSON"),
                eq(personId),
                eq("STAFF_PERSON_CREATED"),
                any(Person.class));
    }

    @Test
    @DisplayName("Debe reutilizar persona existente y emitir evento outbox cuando la CURP ya existe")
    void shouldReuseExistingPersonAndSaveOutboxEvent() {
        // Arrange: Simular que la persona ya existía (ej. era estudiante previo)
        Person existingPerson = Person.builder()
                .id(personId)
                .curp(testCurp)
                .firstName("Irving Efren")
                .firstSurname("García")
                .secondSurname("Ramos")
                .build();

        when(personRepositoryPort.findByCurp(testPerson.getCurp())).thenReturn(Optional.of(existingPerson));

        // Act
        Person result = createStaffPersonService.createStaffPerson(testPerson);

        // Assert
        assertNotNull(result);
        assertEquals(personId, result.getId());

        // Verificaciones clave
        verify(personRepositoryPort, times(1)).findByCurp(testPerson.getCurp());
        verify(personRepositoryPort, never()).save(any()); // Asegura que NUNCA intentó volver a guardar la persona
        verify(outboxEventPort, times(1)).saveEvent(
                eq("STAFF_PERSON"),
                eq(personId),
                eq("STAFF_PERSON_CREATED"),
                eq(existingPerson));
    }
}