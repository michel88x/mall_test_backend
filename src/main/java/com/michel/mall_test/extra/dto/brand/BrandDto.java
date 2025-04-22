package com.michel.mall_test.extra.dto.brand;

import org.springframework.web.multipart.MultipartFile;

public class BrandDto {

    private String slug;
    private String name;
    private String description;
    private MultipartFile file;

    public BrandDto() {}

    public BrandDto(String slug, String name, String description, MultipartFile file) {
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.file = file;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public static final class BrandDtoBuilder {
        private String slug;
        private String name;
        private String description;
        private MultipartFile file;

        private BrandDtoBuilder() {
        }

        public static BrandDtoBuilder aBrandDto() {
            return new BrandDtoBuilder();
        }

        public BrandDtoBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public BrandDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BrandDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BrandDtoBuilder withFile(MultipartFile file) {
            this.file = file;
            return this;
        }

        public BrandDto build() {
            BrandDto brandDto = new BrandDto();
            brandDto.setSlug(slug);
            brandDto.setName(name);
            brandDto.setDescription(description);
            brandDto.setFile(file);
            return brandDto;
        }
    }
}
