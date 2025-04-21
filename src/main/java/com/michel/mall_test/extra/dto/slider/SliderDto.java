package com.michel.mall_test.extra.dto.slider;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class SliderDto {

    private String name;

    private String type;

    private Long targetId;

    private String targetSlug;

    private String url;

    private MultipartFile file;

    public SliderDto() {}

    public SliderDto(String name, String type, Long targetId, String targetSlug, String url, MultipartFile file) {
        this.name = name;
        this.type = type;
        this.targetId = targetId;
        this.targetSlug = targetSlug;
        this.url = url;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public static final class SliderDtoBuilder {
        private @NotEmpty String name;
        private @NotEmpty String type;
        private Long targetId;
        private String targetSlug;
        private String url;
        private MultipartFile file;

        private SliderDtoBuilder() {
        }

        public static SliderDtoBuilder aSliderDto() {
            return new SliderDtoBuilder();
        }

        public SliderDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SliderDtoBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public SliderDtoBuilder withTargetId(Long targetId) {
            this.targetId = targetId;
            return this;
        }

        public SliderDtoBuilder withTargetSlug(String targetSlug) {
            this.targetSlug = targetSlug;
            return this;
        }

        public SliderDtoBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public SliderDtoBuilder withFile(MultipartFile file) {
            this.file = file;
            return this;
        }

        public SliderDto build() {
            SliderDto sliderDto = new SliderDto();
            sliderDto.setName(name);
            sliderDto.setType(type);
            sliderDto.setTargetId(targetId);
            sliderDto.setTargetSlug(targetSlug);
            sliderDto.setUrl(url);
            sliderDto.setFile(file);
            return sliderDto;
        }
    }
}
