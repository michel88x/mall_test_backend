package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @ColumnDefault(value = "0.0")
    private Double totalPrice;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CartProduct> products;

    public Cart() {}

    public Cart(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Cart(User user, Double totalPrice, List<CartProduct> products) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }


    public static final class CartBuilder {
        private User user;
        private Double totalPrice;
        private List<CartProduct> products;

        private CartBuilder() {
        }

        public static CartBuilder aCart() {
            return new CartBuilder();
        }

        public CartBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public CartBuilder withTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public CartBuilder withProducts(List<CartProduct> products) {
            this.products = products;
            return this;
        }

        public Cart build() {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(totalPrice);
            cart.setProducts(products);
            return cart;
        }
    }
}
