package mx.edu.unsis.sige.people.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class PersonContact {

    private UUID personId;
    private String personalEmail;
    private String phoneNumber;
    private String emergencyPhone;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public PersonContact() {
    }

    public PersonContact(UUID personId, String personalEmail, String phoneNumber, String emergencyPhone,
            OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.personId = personId;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.emergencyPhone = emergencyPhone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreateAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(OffsetDateTime updateAt) {
        this.updatedAt = updateAt;
    }
}
