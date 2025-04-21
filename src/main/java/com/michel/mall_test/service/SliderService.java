package com.michel.mall_test.service;

import com.michel.mall_test.entity.Slider;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.slider.SliderDto;
import com.michel.mall_test.extra.enums.SliderType;
import com.michel.mall_test.extra.exceptions.FailedSaveFileException;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
public class SliderService {

    @Autowired
    private SliderRepository sliderRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public BaseResponse getAllSliders(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(sliderRepository.findAll())
                .build();
    }

    public Slider getOneSlider(Long id){
        return sliderRepository.findOne(id);
    }

    public BaseResponse getSlider(Long id){
        Slider slider = getOneSlider(id);
        if(slider != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(slider)
                    .build();
        }
        throw new RecordNotFoundException("The slider does not exist");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        if(getOneSlider(id) != null) {
            sliderRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Slider deleted successfully")
                    .withData(null)
                    .build();
        }else{
            throw new RecordNotFoundException("The slider does not exist");
        }
    }

    @Transactional
    public BaseResponse addNewSlider(SliderDto dto){
        try {
            String filePath = fileStorageService.saveFile(dto.getFile());
            Slider slider = sliderRepository.save(Slider.SliderBuilder.aSlider()
                            .withName(dto.getName())
                            .withType(SliderType.valueOf(dto.getType().toUpperCase()))
                            .withTargetId(dto.getTargetId())
                            .withTargetSlug(dto.getTargetSlug())
                            .withUrl(dto.getUrl())
                            .withImageUrl(filePath)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Slider added successfully")
                    .withData(slider)
                    .build();
        } catch (IOException e) {
            throw new FailedSaveFileException("Failed to upload the image, please try another one");
        }
    }

    @Transactional
    public BaseResponse updateSlider(SliderDto dto, Long id){
        Slider slider = getOneSlider(id);
        if(slider != null) {
            String filePath = "";
            if(dto.getFile() != null){
                try{
                    filePath = fileStorageService.saveFile(dto.getFile());
                } catch (IOException e) {
                    throw new FailedSaveFileException("Failed to upload the image, please try another one");
                }
            }else{
                filePath = slider.getImageUrl();
            }
            Slider newSlider = sliderRepository.save(Slider.SliderBuilder.aSlider()
                            .withId(slider.getId())
                            .withName(dto.getName() != null && !dto.getName().isEmpty()? dto.getName() : slider.getName())
                            .withType(dto.getType() != null && !dto.getType().isEmpty()? SliderType.valueOf(dto.getType().toUpperCase()) : slider.getType())
                            .withTargetId(dto.getTargetId() != null? dto.getTargetId() : slider.getTargetId())
                            .withTargetSlug(dto.getTargetSlug() != null && !dto.getTargetSlug().isEmpty()? dto.getTargetSlug() : slider.getTargetSlug())
                            .withUrl(dto.getUrl() != null && !dto.getUrl().isEmpty()? dto.getUrl() : slider.getUrl())
                            .withImageUrl(filePath)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Slider updated successfully")
                    .withData(newSlider)
                    .build();
        }else{
            throw new RecordNotFoundException("The slider does not exist");
        }
    }
}
