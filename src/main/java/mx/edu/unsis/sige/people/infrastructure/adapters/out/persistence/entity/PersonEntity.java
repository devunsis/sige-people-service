package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persons", schema = "sige_people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity {

    @Id
    private UUID id;

    @Column(name = "curp", length = 18, nullable = false, unique = true)
    private String curp;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "first_surname", length = 50, nullable = false)
    private String firstSurname;

    @Column(name = "second_surname", length = 50)
    private String secondSurname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PersonContactEntity> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PersonAddressEntity> addresses = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    // --- Helper methods for Contacts ---

    public void addContact(PersonContactEntity contact) {
        contacts.add(contact);
        contact.setPerson(this);
    }

    public void removeContact(PersonContactEntity contact) {
        contacts.remove(contact);
        contact.setPerson(null);
    }

    // --- Helper methods for Addresses ---

    public void addAddress(PersonAddressEntity address) {
        addresses.add(address);
        address.setPerson(this);
    }

    public void removeAddress(PersonAddressEntity address) {
        addresses.remove(address);
        address.setPerson(null);
    }
}