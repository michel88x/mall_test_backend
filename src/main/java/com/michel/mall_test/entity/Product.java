package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.michel.mall_test.extra.enums.ProductStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product extends BaseEntity{

    @Column
    private String name;

    @Column
    @Lob
    private String description;

    @Column
    private String slug;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
    @Formula("CASE WHEN remain_in_stock > 0 THEN 'IN_STOCK' ELSE 'OUT_OF_STOCK' END")
    private ProductStatus status;

    @Column
    @ColumnDefault(value = "true")
    private Boolean visibility;

    @Column
    private Double regularPrice;

    @Column
    private Double offerPrice;

    @Formula("CASE WHEN offer_price IS NOT NULL AND offer_price > 0 THEN true ELSE false END")
    private Boolean hasOffer;

    @Formula("CASE WHEN created_at >= NOW() - INTERVAL 1 DAY THEN true ELSE false END")
    private Boolean isNew;

    @Column
    @ColumnDefault(value = "true")
    private Boolean deliveryFree;

    @Column
    private Integer remainInStock;

    @OneToOne()
    @JoinColumn(name = "main_image_id")
    private ProductImage mainImage;

    @OneToMany(mappedBy = "manyProduct", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductImage> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<ProductRate> rates;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<FavouriteProduct> favourites;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<CartProduct> cartProducts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<OrderProduct> orderProducts;

    public Product() {
    }

    public Product(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Product(String name, String description, String slug, ProductStatus status, Boolean visibility, Double regularPrice, Double offerPrice, Boolean hasOffer, Boolean isNew, Boolean deliveryFree, Integer remainInStock, ProductImage mainImage, List<ProductImage> images, Category category, Brand brand, List<ProductRate> rates, List<FavouriteProduct> favourites, List<CartProduct> cartProducts, List<OrderProduct> orderProducts) {
        this.name = name;
        this.description = description;
        this.slug = slug;
        this.status = status;
        this.visibility = visibility;
        this.regularPrice = regularPrice;
        this.offerPrice = offerPrice;
        this.hasOffer = hasOffer;
        this.isNew = isNew;
        this.deliveryFree = deliveryFree;
        this.remainInStock = remainInStock;
        this.mainImage = mainImage;
        this.images = images;
        this.category = category;
        this.brand = brand;
        this.rates = rates;
        this.favourites = favourites;
        this.cartProducts = cartProducts;
        this.orderProducts = orderProducts;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Boolean getHasOffer() {
        return hasOffer;
    }

    public void setHasOffer(Boolean hasOffer) {
        this.hasOffer = hasOffer;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getDeliveryFree() {
        return deliveryFree;
    }

    public void setDeliveryFree(Boolean deliveryFree) {
        this.deliveryFree = deliveryFree;
    }

    public Integer getRemainInStock() {
        return remainInStock;
    }

    public void setRemainInStock(Integer remainInStock) {
        this.remainInStock = remainInStock;
    }

    public ProductImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(ProductImage mainImage) {
        this.mainImage = mainImage;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<ProductRate> getRates() {
        return rates;
    }

    public void setRates(List<ProductRate> rates) {
        this.rates = rates;
    }

    public List<FavouriteProduct> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<FavouriteProduct> favourites) {
        this.favourites = favourites;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }


    public static final class ProductBuilder {
        private String name;
        private String description;
        private String slug;
        private ProductStatus status;
        private Boolean visibility;
        private Boolean favourited;
        private Double regularPrice;
        private Double offerPrice;
        private Boolean hasOffer;
        private Boolean isNew;
        private Boolean deliveryFree;
        private Integer remainInStock;
        private ProductImage mainImage;
        private List<ProductImage> images;
        private Category category;
        private Brand brand;
        private List<ProductRate> rates;
        private List<FavouriteProduct> favourites;
        private List<CartProduct> cartProducts;
        private List<OrderProduct> orderProducts;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public ProductBuilder withStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public ProductBuilder withVisibility(Boolean visibility) {
            this.visibility = visibility;
            return this;
        }

        public ProductBuilder withFavourited(Boolean favourited) {
            this.favourited = favourited;
            return this;
        }

        public ProductBuilder withRegularPrice(Double regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public ProductBuilder withOfferPrice(Double offerPrice) {
            this.offerPrice = offerPrice;
            return this;
        }

        public ProductBuilder withHasOffer(Boolean hasOffer) {
            this.hasOffer = hasOffer;
            return this;
        }

        public ProductBuilder withIsNew(Boolean isNew) {
            this.isNew = isNew;
            return this;
        }

        public ProductBuilder withDeliveryFree(Boolean deliveryFree) {
            this.deliveryFree = deliveryFree;
            return this;
        }

        public ProductBuilder withRemainInStock(Integer remainInStock) {
            this.remainInStock = remainInStock;
            return this;
        }

        public ProductBuilder withMainImage(ProductImage mainImage) {
            this.mainImage = mainImage;
            return this;
        }

        public ProductBuilder withImages(List<ProductImage> images) {
            this.images = images;
            return this;
        }

        public ProductBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public ProductBuilder withBrand(Brand brand) {
            this.brand = brand;
            return this;
        }

        public ProductBuilder withRates(List<ProductRate> rates) {
            this.rates = rates;
            return this;
        }

        public ProductBuilder withFavourites(List<FavouriteProduct> favourites) {
            this.favourites = favourites;
            return this;
        }

        public ProductBuilder withCartProducts(List<CartProduct> cartProducts) {
            this.cartProducts = cartProducts;
            return this;
        }

        public ProductBuilder withOrderProducts(List<OrderProduct> orderProducts) {
            this.orderProducts = orderProducts;
            return this;
        }

        public Product build() {
            return new Product(name, description, slug, status, visibility, regularPrice, offerPrice, hasOffer, isNew, deliveryFree, remainInStock, mainImage, images, category, brand, rates, favourites, cartProducts, orderProducts);
        }
    }
}
