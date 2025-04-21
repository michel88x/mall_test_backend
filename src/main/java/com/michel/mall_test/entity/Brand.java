package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Brand extends BaseEntity{

    @Column
    private String slug;

    @Column
    private String name;

    @Column
    @Lob
    private String description;

    @Column
    private String image;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;

    public Brand() {}

    public Brand(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Brand(String slug, String name, String description, String image, List<Product> products) {
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.image = image;
        this.products = products;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public static final class BrandBuilder {
        private String slug;
        private String name;
        private String description;
        private String image;
        private List<Product> products;

        private BrandBuilder() {
        }

        public static BrandBuilder aBrand() {
            return new BrandBuilder();
        }

        public BrandBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public BrandBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BrandBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BrandBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public BrandBuilder withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Brand build() {
            Brand brand = new Brand();
            brand.setSlug(slug);
            brand.setName(name);
            brand.setDescription(description);
            brand.setImage(image);
            brand.setProducts(products);
            return brand;
        }
    }
}
