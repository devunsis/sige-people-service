package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.mapper;

import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.PersonAddress;
import mx.edu.unsis.sige.people.domain.model.PersonContact;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonAddressEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonContactEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PersonPersistenceMapper {

    public Person toDomain(PersonEntity entity) {
        if (entity == null) {
            return null;
        }

        List<PersonContact> contacts = entity.getContacts() != null
                ? entity.getContacts().stream().map(this::toContactDomain).toList()
                : Collections.emptyList();

        List<PersonAddress> addresses = entity.getAddresses() != null
                ? entity.getAddresses().stream().map(this::toAddressDomain).toList()
                : Collections.emptyList();

        return Person.builder()
                .id(entity.getId())
                .curp(new Curp(entity.getCurp()))
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .firstSurname(entity.getFirstSurname())
                .secondSurname(entity.getSecondSurname())
                .birthDate(entity.getBirthDate())
                .contacts(contacts)
                .addresses(addresses)
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public PersonEntity toEntity(Person domain) {
        if (domain == null) {
            return null;
        }

        PersonEntity entity = PersonEntity.builder()
                .id(domain.getId())
                .curp(domain.getCurp() != null ? domain.getCurp().getValue() : null)
                .firstName(domain.getFirstName())
                .secondName(domain.getSecondName())
                .firstSurname(domain.getFirstSurname())
                .secondSurname(domain.getSecondSurname())
                .birthDate(domain.getBirthDate())
                .createdAt(domain.getCreatedAt())
                .createdBy(domain.getCreatedBy())
                .updatedAt(domain.getUpdatedAt())
                .updatedBy(domain.getUpdatedBy())
                .build();

        if (domain.getContacts() != null) {
            domain.getContacts().forEach(contactDomain -> {
                PersonContactEntity contactEntity = toContactEntity(contactDomain);
                entity.addContact(contactEntity);
            });
        }

        if (domain.getAddresses() != null) {
            domain.getAddresses().forEach(addressDomain -> {
                PersonAddressEntity addressEntity = toAddressEntity(addressDomain);
                entity.addAddress(addressEntity);
            });
        }

        return entity;
    }

    private PersonContact toContactDomain(PersonContactEntity entity) {
        if (entity == null) return null;
        return PersonContact.builder()
                .id(entity.getId())
                .personId(entity.getPerson() != null ? entity.getPerson().getId() : null)
                .contactTypeId(entity.getContactTypeId())
                .value(entity.getValue())
                .description(entity.getDescription())
                .isPrimary(entity.getIsPrimary())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    private PersonContactEntity toContactEntity(PersonContact domain) {
        if (domain == null) return null;
        return PersonContactEntity.builder()
                .id(domain.getId())
                .contactTypeId(domain.getContactTypeId())
                .value(domain.getValue())
                .description(domain.getDescription())
                .isPrimary(domain.getIsPrimary())
                .createdAt(domain.getCreatedAt())
                .createdBy(domain.getCreatedBy())
                .updatedAt(domain.getUpdatedAt())
                .updatedBy(domain.getUpdatedBy())
                .build();
    }

    private PersonAddress toAddressDomain(PersonAddressEntity entity) {
        if (entity == null) return null;
        return PersonAddress.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .exteriorNumber(entity.getExteriorNumber())
                .interiorNumber(entity.getInteriorNumber())
                .neighborhood(entity.getNeighborhood())
                .postalCode(entity.getPostalCode())
                .localityId(entity.getLocalityId())
                .municipalityId(entity.getMunicipalityId())
                .districtId(entity.getDistrictId())
                .regionId(entity.getRegionId())
                .stateId(entity.getStateId())
                .countryId(entity.getCountryId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .addressStatus(entity.getAddressStatus())
                .build();
    }

    private PersonAddressEntity toAddressEntity(PersonAddress domain) {
        if (domain == null) return null;
        return PersonAddressEntity.builder()
                .id(domain.getId())
                .street(domain.getStreet())
                .exteriorNumber(domain.getExteriorNumber())
                .interiorNumber(domain.getInteriorNumber())
                .neighborhood(domain.getNeighborhood())
                .postalCode(domain.getPostalCode())
                .localityId(domain.getLocalityId())
                .municipalityId(domain.getMunicipalityId())
                .districtId(domain.getDistrictId())
                .regionId(domain.getRegionId())
                .stateId(domain.getStateId())
                .countryId(domain.getCountryId())
                .latitude(domain.getLatitude())
                .longitude(domain.getLongitude())
                .addressStatus(domain.getAddressStatus())
                .createdAt(domain.getCreatedAt())
                .createdBy(domain.getCreatedBy())
                .updatedAt(domain.getUpdatedAt())
                .updatedBy(domain.getUpdatedBy())
                .build();
    }
}