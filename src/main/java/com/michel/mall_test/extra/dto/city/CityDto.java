package com.michel.mall_test.extra.dto.city;

public class CityDto {
    private String slug;
    private String name;

    public CityDto() {
    }

    public CityDto(String slug, String name) {
        this.slug = slug;
        this.name = name;
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


    public static final class CityDtoBuilder {
        private String slug;
        private String name;

        private CityDtoBuilder() {
        }

        public static CityDtoBuilder aCityDto() {
            return new CityDtoBuilder();
        }

        public CityDtoBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public CityDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityDto build() {
            CityDto cityDto = new CityDto();
            cityDto.setSlug(slug);
            cityDto.setName(name);
            return cityDto;
        }
    }
}
