package com.michel.mall_test.service;

import com.michel.mall_test.entity.Brand;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.brand.BrandDto;
import com.michel.mall_test.extra.exceptions.FailedSaveFileException;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public BaseResponse getAllBrands(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(brandRepository.findAll())
                .build();
    }

    public Brand getOneBrand(Long id){
        return brandRepository.findOne(id);
    }

    public BaseResponse getBrand(Long id){
        Brand brand = getOneBrand(id);
        if(brand != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(brand)
                    .build();
        }
        throw new RecordNotFoundException("The brand does not exist");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        if(getOneBrand(id) != null) {
            brandRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Brand deleted successfully")
                    .withData(null)
                    .build();
        }else{
            throw new RecordNotFoundException("The brand does not exist");
        }
    }

    @Transactional
    public BaseResponse addNewBrand(BrandDto dto){
        try {
            String filePath = fileStorageService.saveFile(dto.getFile());
            Brand brand = brandRepository.save(Brand.BrandBuilder.aBrand()
                    .withName(dto.getName())
                    .withSlug(dto.getSlug())
                    .withDescription(dto.getDescription())
                    .withImage(filePath)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Brand added successfully")
                    .withData(brand)
                    .build();
        } catch (IOException e) {
            throw new FailedSaveFileException("Failed to upload the image, please try another one");
        }
    }

    @Transactional
    public BaseResponse updateBrand(BrandDto dto, Long id){
        Brand brand = getOneBrand(id);
        if(brand != null) {
            String filePath = "";
            if(dto.getFile() != null){
                try{
                    filePath = fileStorageService.saveFile(dto.getFile());
                } catch (IOException e) {
                    throw new FailedSaveFileException("Failed to upload the image, please try another one");
                }
            }else{
                filePath = brand.getImage();
            }
            Brand newBrand = brandRepository.save(Brand.BrandBuilder.aBrand()
                    .withId(brand.getId())
                    .withName(dto.getName() != null && !dto.getName().isEmpty()? dto.getName() : brand.getName())
                    .withSlug(dto.getSlug() != null && !dto.getSlug().isEmpty()? dto.getSlug() : brand.getSlug())
                    .withDescription(dto.getDescription() != null && !dto.getDescription().isEmpty()? dto.getDescription() : brand.getDescription())
                    .withImage(filePath)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Brand updated successfully")
                    .withData(newBrand)
                    .build();
        }else{
            throw new RecordNotFoundException("The brand does not exist");
        }
    }
}
