package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class OrderProduct extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public OrderProduct() {
    }

    public OrderProduct(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public static final class OrderProductBuilder {
        private Order order;
        private Product product;

        private OrderProductBuilder() {
        }

        public static OrderProductBuilder anOrderProduct() {
            return new OrderProductBuilder();
        }

        public OrderProductBuilder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public OrderProductBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public OrderProduct build() {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            return orderProduct;
        }
    }
}
