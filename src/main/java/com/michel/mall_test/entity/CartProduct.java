package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class CartProduct extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public CartProduct() {}

    public CartProduct(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public CartProduct(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public static final class CartProductBuilder {
        private Cart cart;
        private Product product;

        private CartProductBuilder() {
        }

        public static CartProductBuilder aCartProduct() {
            return new CartProductBuilder();
        }

        public CartProductBuilder withCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public CartProductBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public CartProduct build() {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            return cartProduct;
        }
    }
}
