package mx.edu.unsis.sige.people.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;

public class Person {
    private UUID id;
    private Curp curp;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private LocalDate birthDate;
    private String gender;

    private PersonContact contact;
    private List<PersonAddress> addresses = new ArrayList<>();

    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;

    public Person() {
    }

    // --- Métodos de Reglas del Dominio ---
    public String getFullName() {
        StringBuilder sb = new StringBuilder(firstName);
        if (secondName != null && !secondName.isBlank()) {
            sb.append(" ").append(secondName);
        }
        sb.append(" ").append(firstSurname);
        if (secondSurname != null && !secondSurname.isBlank()) {
            sb.append(" ").append(secondSurname);
        }
        return sb.toString();
    }

    public void addAddress(PersonAddress address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
    }

    // --- Getters y Setters ---

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Curp getCurp() {
        return curp;
    }

    public void setCurp(Curp curp) {
        this.curp = curp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PersonContact getContact() {
        return contact;
    }

    public void setContact(PersonContact contact) {
        this.contact = contact;
    }

    public List<PersonAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<PersonAddress> addresses) {
        this.addresses = addresses;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

}
