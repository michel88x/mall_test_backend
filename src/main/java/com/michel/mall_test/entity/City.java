package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class City extends BaseEntity{

    @Column
    private String slug;

    @Column
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Address> addresses;

    public City() {}

    public City(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public City(String slug, String name, List<Address> addresses) {
        this.slug = slug;
        this.name = name;
        this.addresses = addresses;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public static final class CityBuilder {
        private Long id;
        private String slug;
        private String name;
        private List<Address> addresses;

        private CityBuilder() {
        }

        public static CityBuilder aCity() {
            return new CityBuilder();
        }

        public CityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CityBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public CityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public City build() {
            City city = new City();
            city.setId(id);
            city.setSlug(slug);
            city.setName(name);
            city.setAddresses(addresses);
            return city;
        }
    }
}
