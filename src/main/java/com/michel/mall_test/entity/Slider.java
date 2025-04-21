package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.michel.mall_test.extra.enums.SliderType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Slider extends BaseEntity{
    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
    @Column(name = "type", columnDefinition = "ENUM('GENERAL','EXTERNAL','PRODUCT') DEFAULT 'GENERAL'")
    private SliderType type;

    @Column
    private Long targetId;

    @Column
    private String targetSlug;

    @Column
    @Lob
    private String url;

    @Column
    @Lob
    private String imageUrl;

    public Slider() {}

    public Slider(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public Slider(String name, SliderType type, Long targetId, String targetSlug, String url, String imageUrl) {
        this.name = name;
        this.type = type;
        this.targetId = targetId;
        this.targetSlug = targetSlug;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SliderType getType() {
        return type;
    }

    public void setType(SliderType type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getTargetSlug() {
        return targetSlug;
    }

    public void setTargetSlug(String targetSlug) {
        this.targetSlug = targetSlug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final class SliderBuilder {
        private Long id;
        private String name;
        private SliderType type;
        private Long targetId;
        private String targetSlug;
        private String url;
        private String imageUrl;

        private SliderBuilder() {
        }

        public static SliderBuilder aSlider() {
            return new SliderBuilder();
        }

        public SliderBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SliderBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SliderBuilder withType(SliderType type) {
            this.type = type;
            return this;
        }

        public SliderBuilder withTargetId(Long targetId) {
            this.targetId = targetId;
            return this;
        }

        public SliderBuilder withTargetSlug(String targetSlug) {
            this.targetSlug = targetSlug;
            return this;
        }

        public SliderBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public SliderBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Slider build() {
            Slider slider = new Slider();
            slider.setId(id);
            slider.setName(name);
            slider.setType(type);
            slider.setTargetId(targetId);
            slider.setTargetSlug(targetSlug);
            slider.setUrl(url);
            slider.setImageUrl(imageUrl);
            return slider;
        }
    }
}
