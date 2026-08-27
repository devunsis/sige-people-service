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
    private UUID personId;
    private UUID addressTypeId;
    
    private String street;
    private String exteriorNumber;
    private String interiorNumber;
    private String neighborhood;
    private String postalCode;
    private String locality;
    private String municipality;
    private String state;
    private String country;
    
    private Boolean isPrimary;
    
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
}