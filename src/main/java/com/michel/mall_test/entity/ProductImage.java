package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProductImage extends BaseEntity{

    @Column
    private String path;

    @OneToOne(mappedBy = "mainImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Product oneProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "many_product_id")
    @JsonBackReference
    private Product manyProduct;

    public ProductImage() {
    }

    public ProductImage(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public ProductImage(String path, Product oneProduct, Product manyProduct) {
        this.path = path;
        this.oneProduct = oneProduct;
        this.manyProduct = manyProduct;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product getOneProduct() {
        return oneProduct;
    }

    public void setOneProduct(Product oneProduct) {
        this.oneProduct = oneProduct;
    }

    public Product getManyProduct() {
        return manyProduct;
    }

    public void setManyProduct(Product manyProduct) {
        this.manyProduct = manyProduct;
    }


    public static final class ProductImageBuilder {
        private String path;
        private Product oneProduct;
        private Product manyProduct;

        private ProductImageBuilder() {
        }

        public static ProductImageBuilder aProductImage() {
            return new ProductImageBuilder();
        }

        public ProductImageBuilder withPath(String path) {
            this.path = path;
            return this;
        }

        public ProductImageBuilder withOneProduct(Product oneProduct) {
            this.oneProduct = oneProduct;
            return this;
        }

        public ProductImageBuilder withManyProduct(Product manyProduct) {
            this.manyProduct = manyProduct;
            return this;
        }

        public ProductImage build() {
            ProductImage productImage = new ProductImage();
            productImage.setPath(path);
            productImage.setOneProduct(oneProduct);
            productImage.setManyProduct(manyProduct);
            return productImage;
        }
    }
}
