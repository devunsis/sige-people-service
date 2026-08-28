package mx.edu.unsis.sige.people.infrastucture.adapters.out.persistence.mapper;

// Ajusta las importaciones según los paquetes exactos de tu proyecto
import mx.edu.unsis.sige.people.domain.model.PersonAddress;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonAddressEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.PersonEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PersonMapperTest {

    @Test
    void shouldMapPersonAddressEntityToDomain() {
        // Arrange
        UUID addressId = UUID.randomUUID();
        UUID personId = UUID.randomUUID();
        PersonEntity personEntity = PersonEntity.builder().id(personId).build();

        PersonAddressEntity entity = PersonAddressEntity.builder()
                .id(addressId)
                .person(personEntity)
                .street("Av. Universidad")
                .exteriorNumber("100")
                .interiorNumber("A")
                .neighborhood("Centro")
                .postalCode("70800")
                .localityId("LOC-01")
                .municipalityId("MUN-01")
                .districtId("DIS-01")
                .regionId("REG-01")
                .stateId("OAX")
                .countryId("MEX")
                .addressStatus("ACTIVE")
                .build();

        // Act & Assert (Ajusta la llamada según tu mapper de persistencia actual)
        // PersonAddress domain = personPersistenceMapper.toDomain(entity);
        // assertThat(domain.getMunicipalityId()).isEqualTo("MUN-01");
    }

    @Test
    void shouldMapPersonAddressDomainToEntity() {
        // Arrange
        UUID addressId = UUID.randomUUID();

        PersonAddress domain = PersonAddress.builder()
                .id(addressId)
                .street("Av. Universidad")
                .exteriorNumber("100")
                .neighborhood("Centro")
                .postalCode("70800")
                .localityId("LOC-01")
                .municipalityId("MUN-01")
                .districtId("DIS-01")
                .regionId("REG-01")
                .stateId("OAX")
                .countryId("MEX")
                .addressStatus("ACTIVE")
                .build();

        // Act & Assert (Ajusta la llamada según tu mapper)
        // PersonAddressEntity entity = personPersistenceMapper.toEntity(domain);
        // assertThat(entity.getMunicipalityId()).isEqualTo("MUN-01");
    }
}