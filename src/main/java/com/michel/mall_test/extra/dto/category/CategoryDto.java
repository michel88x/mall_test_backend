package com.michel.mall_test.extra.dto.category;

import org.springframework.web.multipart.MultipartFile;

public class CategoryDto {
    private String slug;
    private Boolean visibility;
    private String name;
    private String description;
    private MultipartFile file;
    private Long parentCategoryId;

    public CategoryDto() {}

    public CategoryDto(String slug, Boolean visibility, String name, String description, MultipartFile file, Long parentCategoryId) {
        this.slug = slug;
        this.visibility = visibility;
        this.name = name;
        this.description = description;
        this.file = file;
        this.parentCategoryId = parentCategoryId;
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

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }


    public static final class CategoryDtoBuilder {
        private String slug;
        private Boolean visibility;
        private String name;
        private String description;
        private MultipartFile file;
        private Long parentCategoryId;

        private CategoryDtoBuilder() {
        }

        public static CategoryDtoBuilder aCategoryDto() {
            return new CategoryDtoBuilder();
        }

        public CategoryDtoBuilder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public CategoryDtoBuilder withVisibility(Boolean visibility) {
            this.visibility = visibility;
            return this;
        }

        public CategoryDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CategoryDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CategoryDtoBuilder withFile(MultipartFile file) {
            this.file = file;
            return this;
        }

        public CategoryDtoBuilder withParentCategoryId(Long parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
            return this;
        }

        public CategoryDto build() {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setSlug(slug);
            categoryDto.setVisibility(visibility);
            categoryDto.setName(name);
            categoryDto.setDescription(description);
            categoryDto.setFile(file);
            categoryDto.setParentCategoryId(parentCategoryId);
            return categoryDto;
        }
    }
}
