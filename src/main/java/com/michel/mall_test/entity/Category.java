package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Category extends BaseEntity{

    @Column
    private String slug;

    @Column
    @ColumnDefault(value = "true")
    private Boolean visibility;

    @Column
    private String name;

    @Column
    @Lob
    private String description;

    @Column
    private String image;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Category> subCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parentCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;

    public Category() {}

    public Category(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Category(String slug, Boolean visibility, String name, String description, String image, List<Category> subCategories, Category parentCategory, List<Product> products) {
        this.slug = slug;
        this.visibility = visibility;
        this.name = name;
        this.description = description;
        this.image = image;
        this.subCategories = subCategories;
        this.parentCategory = parentCategory;
        this.products = products;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
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

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public static final class CategoryBuilder {
        private String slug;
        private Boolean visibility;
        private String name;
        private String description;
        private String image;
        private List<Category> subCategories;
        private Category parentCategory;
        private List<Product> products;

        private CategoryBuilder() {
        }

        public static CategoryBuilder aCategory() {
            return new CategoryBuilder();
        }

        public CategoryBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public CategoryBuilder withVisibility(Boolean visibility) {
            this.visibility = visibility;
            return this;
        }

        public CategoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CategoryBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public CategoryBuilder withSubCategories(List<Category> subCategories) {
            this.subCategories = subCategories;
            return this;
        }

        public CategoryBuilder withParentCategory(Category parentCategory) {
            this.parentCategory = parentCategory;
            return this;
        }

        public CategoryBuilder withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.setSlug(slug);
            category.setVisibility(visibility);
            category.setName(name);
            category.setDescription(description);
            category.setImage(image);
            category.setSubCategories(subCategories);
            category.setParentCategory(parentCategory);
            category.setProducts(products);
            return category;
        }
    }
}
