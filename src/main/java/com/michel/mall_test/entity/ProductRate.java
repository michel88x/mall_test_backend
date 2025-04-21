package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
public class ProductRate extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Column
    @ColumnDefault(value = "0.0")
    private Double rate;

    @Column
    @Lob
    private String review;

    public ProductRate() {
    }

    public ProductRate(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public ProductRate(User user, Product product, Double rate, String review) {
        this.user = user;
        this.product = product;
        this.rate = rate;
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    public static final class ProductRateBuilder {
        private User user;
        private Product product;
        private Double rate;
        private String review;

        private ProductRateBuilder() {
        }

        public static ProductRateBuilder aProductRates() {
            return new ProductRateBuilder();
        }

        public ProductRateBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public ProductRateBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public ProductRateBuilder withRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public ProductRateBuilder withReview(String review) {
            this.review = review;
            return this;
        }

        public ProductRate build() {
            ProductRate productRates = new ProductRate();
            productRates.setUser(user);
            productRates.setProduct(product);
            productRates.setRate(rate);
            productRates.setReview(review);
            return productRates;
        }
    }
}
