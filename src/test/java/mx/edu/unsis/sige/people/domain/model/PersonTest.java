package mx.edu.unsis.sige.people.domain.model;

import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    @DisplayName("Should build Person successfully with contacts list")
    void shouldBuildPersonSuccessfully() {
        // Given
        UUID id = UUID.randomUUID();
        Curp curp = new Curp("GARG900101HOCMXX01");
        PersonContact contact = PersonContact.builder()
                .id(UUID.randomUUID())
                .value("usuario@unsis.edu.mx")
                .isPrimary(true)
                .build();

        // When
        Person person = Person.builder()
                .id(id)
                .curp(curp)
                .firstName("Irving")
                .firstSurname("García")
                .birthDate(LocalDate.of(1990, 1, 1))
                .contacts(List.of(contact))
                .build();

        // Then
        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(id);
        assertThat(person.getCurp()).isEqualTo(curp);
        assertThat(person.getFirstName()).isEqualTo("Irving");
        assertThat(person.getContacts()).hasSize(1);
        assertThat(person.getContacts()).contains(contact);
    }
}