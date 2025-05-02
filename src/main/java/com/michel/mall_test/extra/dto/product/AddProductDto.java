package com.michel.mall_test.extra.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AddProductDto {
    private String name;
    private String description;
    private String slug;
    private Boolean visibility;
    private Double regularPrice;
    private Double offerPrice;
    private Boolean deliveryFree;
    private Integer remainInStock;
    private MultipartFile mainImageFile;
    private List<MultipartFile> images;
    private Long categoryId;
    private Long brandId;

    public AddProductDto() {}

    public AddProductDto(String name,
                         String description,
                         String slug,
                         Boolean visibility,
                         Double regularPrice,
                         Double offerPrice,
                         Boolean deliveryFree,
                         Integer remainInStock,
                         MultipartFile mainImageFile,
                         List<MultipartFile> images,
                         Long categoryId,
                         Long brandId) {
        this.name = name;
        this.description = description;
        this.slug = slug;
        this.visibility = visibility;
        this.regularPrice = regularPrice;
        this.offerPrice = offerPrice;
        this.deliveryFree = deliveryFree;
        this.remainInStock = remainInStock;
        this.mainImageFile = mainImageFile;
        this.images = images;
        this.categoryId = categoryId;
        this.brandId = brandId;
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

    public MultipartFile getMainImageFile() {
        return mainImageFile;
    }

    public void setMainImageFile(MultipartFile mainImageFile) {
        this.mainImageFile = mainImageFile;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }


    public static final class AddProductDtoBuilder {
        private String name;
        private String description;
        private String slug;
        private Boolean visibility;
        private Double regularPrice;
        private Double offerPrice;
        private Boolean deliveryFree;
        private Integer remainInStock;
        private MultipartFile mainImageFile;
        private List<MultipartFile> images;
        private Long categoryId;
        private Long brandId;

        private AddProductDtoBuilder() {
        }

        public static AddProductDtoBuilder anAddProductDto() {
            return new AddProductDtoBuilder();
        }

        public AddProductDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AddProductDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public AddProductDtoBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public AddProductDtoBuilder withVisibility(Boolean visibility) {
            this.visibility = visibility;
            return this;
        }

        public AddProductDtoBuilder withRegularPrice(Double regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public AddProductDtoBuilder withOfferPrice(Double offerPrice) {
            this.offerPrice = offerPrice;
            return this;
        }

        public AddProductDtoBuilder withDeliveryFree(Boolean deliveryFree) {
            this.deliveryFree = deliveryFree;
            return this;
        }

        public AddProductDtoBuilder withRemainInStock(Integer remainInStock) {
            this.remainInStock = remainInStock;
            return this;
        }

        public AddProductDtoBuilder withMainImageFile(MultipartFile mainImageFile) {
            this.mainImageFile = mainImageFile;
            return this;
        }

        public AddProductDtoBuilder withImages(List<MultipartFile> images) {
            this.images = images;
            return this;
        }

        public AddProductDtoBuilder withCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public AddProductDtoBuilder withBrandId(Long brandId) {
            this.brandId = brandId;
            return this;
        }

        public AddProductDto build() {
            AddProductDto addProductDto = new AddProductDto();
            addProductDto.setName(name);
            addProductDto.setDescription(description);
            addProductDto.setSlug(slug);
            addProductDto.setVisibility(visibility);
            addProductDto.setRegularPrice(regularPrice);
            addProductDto.setOfferPrice(offerPrice);
            addProductDto.setDeliveryFree(deliveryFree);
            addProductDto.setRemainInStock(remainInStock);
            addProductDto.setMainImageFile(mainImageFile);
            addProductDto.setImages(images);
            addProductDto.setCategoryId(categoryId);
            addProductDto.setBrandId(brandId);
            return addProductDto;
        }
    }
}
