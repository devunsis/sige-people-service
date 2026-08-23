package mx.edu.unsis.sige.people.domain.ports.in;

import mx.edu.unsis.sige.people.domain.model.Person;

public interface CreatePersonUseCase {

    Person createPerson(CreatePersonCommand command);

    record CreatePersonCommand(
            String curp,
            String firstName,
            String secondName,
            String firstSurname,
            String secondSurname,
            String birthDate, // Formato YYYY-MM-DD
            String gender,
            String personalEmail,
            String phoneNumber,
            String emergencyPhone,
            String street,
            String exteriorNumber,
            String interiorNumber,
            String neighborhood,
            String postalCode,
            String localityId,
            String municipalityId,
            String districtId,
            String regionId,
            String stateId,
            String countryId) {
    }
}