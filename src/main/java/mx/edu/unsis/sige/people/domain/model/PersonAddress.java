package mx.edu.unsis.sige.people.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonAddress {
    private UUID id;
    private String street;
    private String exteriorNumber;
    private String interiorNumber;
    private String neighborhood;
    private String postalCode;
    private String localityId;
    private String municipalityId;
    private String districtId;
    private String regionId;
    private String stateId;
    private String countryId;
    private Double latitude;
    private Double longitude;
    private String addressStatus;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}