package com.michel.mall_test.extra.dto.address;

import com.michel.mall_test.entity.Address;
import com.michel.mall_test.entity.City;
import com.michel.mall_test.entity.User;
import com.michel.mall_test.service.AddressService;

public class AddressDto {
    private String name;
    private String coordinates;
    private String streetName;
    private Integer buildingNumber;
    private Integer floorNumber;
    private Integer apartmentNumber;
    private Boolean isDefault;
    private String note;
    private User user;
    private Long city;

    public AddressDto() {}

    public AddressDto(String name, String coordinates, String streetName, Integer buildingNumber, Integer floorNumber, Integer apartmentNumber, Boolean isDefault, String note, User user, Long city) {
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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }


    public static final class AddressServiceBuilder {
        private String name;
        private String coordinates;
        private String streetName;
        private Integer buildingNumber;
        private Integer floorNumber;
        private Integer apartmentNumber;
        private Boolean isDefault;
        private String note;
        private User user;
        private Long city;

        private AddressServiceBuilder() {
        }

        public static AddressServiceBuilder anAddressService() {
            return new AddressServiceBuilder();
        }

        public AddressServiceBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AddressServiceBuilder withCoordinates(String coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public AddressServiceBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressServiceBuilder withBuildingNumber(Integer buildingNumber) {
            this.buildingNumber = buildingNumber;
            return this;
        }

        public AddressServiceBuilder withFloorNumber(Integer floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public AddressServiceBuilder withApartmentNumber(Integer apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public AddressServiceBuilder withIsDefault(Boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public AddressServiceBuilder withNote(String note) {
            this.note = note;
            return this;
        }

        public AddressServiceBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AddressServiceBuilder withCity(Long city) {
            this.city = city;
            return this;
        }

        public AddressDto build() {
            AddressDto address = new AddressDto();
            address.setName(name);
            address.setCoordinates(coordinates);
            address.setStreetName(streetName);
            address.setBuildingNumber(buildingNumber);
            address.setFloorNumber(floorNumber);
            address.setApartmentNumber(apartmentNumber);
            address.setDefault(isDefault);
            address.setNote(note);
            address.setUser(user);
            address.setCity(city);
            return address;
        }
    }
}
