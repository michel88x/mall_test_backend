package com.michel.mall_test.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class FavouriteProduct extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public FavouriteProduct() {}

    public FavouriteProduct(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public FavouriteProduct(User user, Product product) {
        this.user = user;
        this.product = product;
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


    public static final class FavouriteProductBuilder {
        private User user;
        private Product product;

        private FavouriteProductBuilder() {
        }

        public static FavouriteProductBuilder aFavouriteProduct() {
            return new FavouriteProductBuilder();
        }

        public FavouriteProductBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public FavouriteProductBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public FavouriteProduct build() {
            FavouriteProduct favouriteProduct = new FavouriteProduct();
            favouriteProduct.setUser(user);
            favouriteProduct.setProduct(product);
            return favouriteProduct;
        }
    }
}
