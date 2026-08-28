package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "person_addresses", schema = "sige_people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "exterior_number", nullable = false, length = 20)
    private String exteriorNumber;

    @Column(name = "interior_number", length = 20)
    private String interiorNumber;

    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(name = "locality_id", nullable = false, length = 10)
    private String localityId;

    @Column(name = "municipality_id", nullable = false, length = 10)
    private String municipalityId;

    @Column(name = "district_id", nullable = false, length = 10)
    private String districtId;

    @Column(name = "region_id", nullable = false, length = 10)
    private String regionId;

    @Column(name = "state_id", nullable = false, length = 10)
    private String stateId;

    @Column(name = "country_id", nullable = false, length = 10)
    private String countryId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "address_status", nullable = false, length = 20)
    private String addressStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by")
    private UUID updatedBy;
}