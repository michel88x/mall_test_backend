package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.michel.mall_test.extra.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "_order")
public class Order extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
    @Column(name = "role", columnDefinition = "ENUM('FINISHED','PENDING','PROCESSING','REJECTED') DEFAULT 'PENDING'")
    private OrderStatus role;

    @Column
    @ColumnDefault(value = "0.0")
    private Double finalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderProduct> products;

    public Order() {}

    public Order(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Order(User user, OrderStatus role, Double finalPrice, List<OrderProduct> products) {
        this.user = user;
        this.role = role;
        this.finalPrice = finalPrice;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getRole() {
        return role;
    }

    public void setRole(OrderStatus role) {
        this.role = role;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }


    public static final class OrderBuilder {
        private User user;
        private OrderStatus role;
        private Double finalPrice;
        private List<OrderProduct> products;

        private OrderBuilder() {
        }

        public static OrderBuilder anOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder withRole(OrderStatus role) {
            this.role = role;
            return this;
        }

        public OrderBuilder withFinalPrice(Double finalPrice) {
            this.finalPrice = finalPrice;
            return this;
        }

        public OrderBuilder withProducts(List<OrderProduct> products) {
            this.products = products;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setUser(user);
            order.setRole(role);
            order.setFinalPrice(finalPrice);
            order.setProducts(products);
            return order;
        }
    }
}
