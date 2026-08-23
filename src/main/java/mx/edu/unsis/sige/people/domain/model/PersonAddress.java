package mx.edu.unsis.sige.people.domain.model;

import java.time.OffsetDateTime;

import org.springframework.data.geo.Point;
import java.util.UUID;

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
    private Point geomLocation;
    private String addressStatus;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;

    public PersonAddress() {
    }

    public PersonAddress(UUID id, String street, String exteriorNumber, String interiorNumber,
            String neighborhood, String postalCode, String localityId, String municipalityId,
            String districtId, String regionId, String stateId, String countryId,
            Point geomLocation, String addressStatus, OffsetDateTime createdAt,
            UUID createdBy, OffsetDateTime updatedAt, UUID updatedBy) {
        this.id = id;
        this.street = street;
        this.exteriorNumber = exteriorNumber;
        this.interiorNumber = interiorNumber;
        this.neighborhood = neighborhood;
        this.postalCode = postalCode;
        this.localityId = localityId;
        this.municipalityId = municipalityId;
        this.districtId = districtId;
        this.regionId = regionId;
        this.stateId = stateId;
        this.countryId = countryId;
        this.geomLocation = geomLocation;
        this.addressStatus = addressStatus;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getExteriorNumber() {
        return exteriorNumber;
    }

    public void setExteriorNumber(String exteriorNumber) {
        this.exteriorNumber = exteriorNumber;
    }

    public String getInteriorNumber() {
        return interiorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocalityId() {
        return localityId;
    }

    public void setLocalityId(String localityId) {
        this.localityId = localityId;
    }

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Point getGeomLocation() {
        return geomLocation;
    }

    public void setGeomLocation(Point geomLocation) {
        this.geomLocation = geomLocation;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
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
