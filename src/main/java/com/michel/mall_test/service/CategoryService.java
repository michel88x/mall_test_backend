package com.michel.mall_test.service;

import com.michel.mall_test.entity.Brand;
import com.michel.mall_test.entity.Category;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.brand.BrandDto;
import com.michel.mall_test.extra.dto.category.CategoryDto;
import com.michel.mall_test.extra.exceptions.FailedSaveFileException;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public BaseResponse getAllCategories(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(categoryRepository.getParentCategories())
                .build();
    }

    public Category getOneCategory(Long id){
        return categoryRepository.findOne(id);
    }

    public BaseResponse getCategory(Long id){
        Category category = getOneCategory(id);
        if(category != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(category)
                    .build();
        }
        throw new RecordNotFoundException("The category does not exist");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        if(getOneCategory(id) != null) {
            categoryRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Category deleted successfully")
                    .withData(null)
                    .build();
        }else{
            throw new RecordNotFoundException("The category does not exist");
        }
    }

    @Transactional
    public BaseResponse addNewCategory(CategoryDto dto){
        try {
            Category parentCategory = null;
            if(dto.getParentCategoryId() != null){
                parentCategory = categoryRepository.findOne(dto.getParentCategoryId());
                if(parentCategory == null){
                    throw new RecordNotFoundException("The parent category is not exist");
                }
            }
            String filePath = fileStorageService.saveFile(dto.getFile());
            Category category = categoryRepository.save(Category.CategoryBuilder.aCategory()
                    .withName(dto.getName())
                    .withSlug(dto.getSlug())
                    .withDescription(dto.getDescription())
                    .withVisibility(dto.getVisibility() != null? dto.getVisibility() : true)
                    .withParentCategory(parentCategory)
                    .withImage(filePath)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Category added successfully")
                    .withData(category)
                    .build();
        } catch (IOException e) {
            throw new FailedSaveFileException("Failed to upload the image, please try another one");
        }
    }

    @Transactional
    public BaseResponse updateCategory(CategoryDto dto, Long id){
        Category category = getOneCategory(id);
        if(category != null) {
            Category parentCategory = category.getParentCategory();
            if(dto.getParentCategoryId() != null){
                Category p = categoryRepository.findOne(dto.getParentCategoryId());
                if(p != null){
                    parentCategory = p;
                }else{
                    throw new RecordNotFoundException("The parent category is not exist");
                }
            }
            String oldImagePath = category.getImage();
            boolean deleteOldImage = false;
            String filePath = "";
            if(dto.getFile() != null){
                try{
                    filePath = fileStorageService.saveFile(dto.getFile());
                    deleteOldImage = true;
                } catch (IOException e) {
                    throw new FailedSaveFileException("Failed to upload the image, please try another one");
                }
            }else{
                filePath = oldImagePath;
            }
            Category newCategory = categoryRepository.save(Category.CategoryBuilder.aCategory()
                    .withId(category.getId())
                    .withName(dto.getName() != null && !dto.getName().isEmpty()? dto.getName() : category.getName())
                    .withSlug(dto.getSlug() != null && !dto.getSlug().isEmpty()? dto.getSlug() : category.getSlug())
                    .withDescription(dto.getDescription() != null && !dto.getDescription().isEmpty()? dto.getDescription() : category.getDescription())
                    .withVisibility(dto.getVisibility() != null? dto.getVisibility() : category.getVisibility())
                    .withParentCategory(parentCategory)
                    .withImage(filePath)
                    .build());
            if(deleteOldImage){
                fileStorageService.deleteFile(oldImagePath);
            }
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Category updated successfully")
                    .withData(newCategory)
                    .build();
        }else{
            throw new RecordNotFoundException("The category does not exist");
        }
    }

    @Transactional
    public BaseResponse updateVisibility(Long id, Boolean visibility){
        Category category = getOneCategory(id);
        if(category != null){
            categoryRepository.updateVisibility(id, visibility);
            category.setVisibility(visibility);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("The visibility of the category updated successfully")
                    .withData(category)
                    .build();
        }
        throw new RecordNotFoundException("The category does not exist");
    }
}
