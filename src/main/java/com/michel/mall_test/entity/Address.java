package com.michel.mall_test.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
public class Address extends BaseEntity{

    @Column
    private String name;

    @Column
    private String coordinates;

    @Column
    private String streetName;

    @Column
    private Integer buildingNumber;

    @Column
    private Integer floorNumber;

    @Column
    private Integer apartmentNumber;

    @Column
    @ColumnDefault("false")
    private Boolean isDefault;

    @Column
    @Lob
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;


    public Address() {}

    public Address(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Address(String name, String coordinates, String streetName, Integer buildingNumber, Integer floorNumber, Integer apartmentNumber, Boolean isDefault, String note, User user, City city) {
        this.name = name;
        this.coordinates = coordinates;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.floorNumber = floorNumber;
        this.apartmentNumber = apartmentNumber;
        this.isDefault = isDefault;
        this.note = note;
        this.user = user;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public static final class AddressBuilder {
        private Long id;
        private String name;
        private String coordinates;
        private String streetName;
        private Integer buildingNumber;
        private Integer floorNumber;
        private Integer apartmentNumber;
        private Boolean isDefault;
        private String note;
        private User user;
        private City city;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AddressBuilder withCoordinates(String coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withBuildingNumber(Integer buildingNumber) {
            this.buildingNumber = buildingNumber;
            return this;
        }

        public AddressBuilder withFloorNumber(Integer floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public AddressBuilder withApartmentNumber(Integer apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public AddressBuilder withIsDefault(Boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public AddressBuilder withNote(String note) {
            this.note = note;
            return this;
        }

        public AddressBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AddressBuilder withCity(City city) {
            this.city = city;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setId(id);
            address.setNote(name);
            address.setCoordinates(coordinates);
            address.setStreetName(streetName);
            address.setBuildingNumber(buildingNumber);
            address.setFloorNumber(floorNumber);
            address.setApartmentNumber(apartmentNumber);
            address.setDefault(isDefault);
            address.setNote(note);
            address.setUser(user);
            address.setCity(city);
            return new Address(name, coordinates, streetName, buildingNumber, floorNumber, apartmentNumber, isDefault, note, user, city);
        }
    }
}
