package com.michel.mall_test.service;

import com.michel.mall_test.entity.Brand;
import com.michel.mall_test.entity.Category;
import com.michel.mall_test.entity.Product;
import com.michel.mall_test.entity.ProductImage;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.product.AddProductDto;
import com.michel.mall_test.extra.exceptions.FailedSaveFileException;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    public BaseResponse getAllProducts(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(repository.findAll())
                .build();
    }

    @Transactional
    public BaseResponse addNewProduct(AddProductDto dto){
        try{
            String mainImagePath = fileStorageService.saveFile(dto.getMainImageFile());
            System.out.println("DtoMainImagePath: " + dto.getMainImageFile().getOriginalFilename());
            System.out.println("UploadedImageUrl: " + mainImagePath);
            List<String> imagesPaths = dto.getImages().stream()
                    .map(image -> {
                        try {
                            return fileStorageService.saveFile(image);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
            Category category = categoryRepository.findOne(dto.getCategoryId());
            if(category == null){
                throw new RecordNotFoundException("The category is not exist");
            }
            Brand brand = brandRepository.findOne(dto.getBrandId());
            if(brand == null){
                throw new RecordNotFoundException("The brand is not exist");
            }
            //Save the product
            Product product = repository.save(Product.ProductBuilder.aProduct()
                            .withName(dto.getName())
                            .withDescription(dto.getDescription())
                            .withSlug(dto.getSlug())
                            .withVisibility(dto.getVisibility())
                            .withRegularPrice(dto.getRegularPrice())
                            .withOfferPrice(dto.getOfferPrice())
                            .withDeliveryFree(dto.getDeliveryFree())
                            .withRemainInStock(dto.getRemainInStock())
                            .withCategory(category)
                            .withBrand(brand)
                    .build());
            //Save the images
            ProductImage mainImage = productImageRepository.save(ProductImage.ProductImageBuilder.aProductImage()
                            .withPath(mainImagePath)
                    .build());
            product.setMainImage(mainImage);
            Product updatedProduct = repository.save(product);
            List<ProductImage> images = imagesPaths.stream()
                    .map(image -> productImageRepository.save(ProductImage.ProductImageBuilder.aProductImage()
                                    .withPath(image)
                                    .withManyProduct(updatedProduct)
                            .build())).toList();
            updatedProduct.setImages(images);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Product added successfully")
                    .withData(updatedProduct)
                    .build();
        } catch (Exception e) {
            throw new FailedSaveFileException("Failed to upload the image, please try another one");
        }
    }

    public Product getProduct(Long id){
        return repository.findOne(id);
    }

    @Transactional
    public BaseResponse updateProduct(Long id, AddProductDto dto){
        Product product = getProduct(id);
        if(product != null){
            if(dto.getCategoryId() != null){
                Category category = categoryRepository.findOne(dto.getCategoryId());
                if(category == null){
                    throw new RecordNotFoundException("The category is not exist");
                }
                product.setCategory(category);
            }
            if(dto.getBrandId() != null){
                Brand brand = brandRepository.findOne(dto.getCategoryId());
                if(brand == null){
                    throw new RecordNotFoundException("The brand is not exist");
                }
                product.setBrand(brand);
            }
            if(dto.getMainImageFile() != null){
                fileStorageService.deleteFile(product.getMainImage().getPath());
                try {
                    String newMainImagePath = fileStorageService.saveFile(dto.getMainImageFile());
                    ProductImage productImage = productImageRepository.findOne(product.getMainImage().getId());
                    productImage.setPath(newMainImagePath);
                    productImageRepository.save(productImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(dto.getName() != null && !dto.getName().isEmpty()) product.setName(dto.getName());
            if(dto.getDescription() != null && !dto.getDescription().isEmpty()) product.setDescription(dto.getDescription());
            if(dto.getSlug() != null && !dto.getSlug().isEmpty()) product.setSlug(dto.getSlug());
            if(dto.getVisibility() != null) product.setVisibility(dto.getVisibility());
            if(dto.getRegularPrice() != null) product.setRegularPrice(dto.getRegularPrice());
            if(dto.getOfferPrice() != null) product.setOfferPrice(dto.getOfferPrice());
            if(dto.getDeliveryFree() != null) product.setDeliveryFree(dto.getDeliveryFree());
            if(dto.getRemainInStock() != null) product.setRemainInStock(dto.getRemainInStock());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Product updated successfully")
                    .withData(product)
                    .build();
        }
        throw new RecordNotFoundException("The product is not exist");
    }

    public BaseResponse getProductDetails(Long id){
        Product product = getProduct(id);
        if(product != null){
            //Used LinkedHashMap for preserving the sorting of the fields order when returning the result from the endpoint
            Map<String, Object> result = new LinkedHashMap<>();
            Map<String, Object> brandMap = new LinkedHashMap<>();
            Map<String, Object> categoryMap = new LinkedHashMap<>();
            result.put("id", product.getId());
            result.put("name", product.getName());
            result.put("description", product.getDescription());
            result.put("slug", product.getSlug());
            result.put("status", product.getStatus());
            result.put("regularPrice", product.getRegularPrice());
            result.put("offerPrice", product.getOfferPrice());
            result.put("hasOffer", product.getHasOffer());
            result.put("new", product.getNew());
            result.put("deliveryFree", product.getDeliveryFree());
            result.put("mainImage", product.getMainImage());
            result.put("images", product.getImages());
            Brand brand = product.getBrand();
            brandMap.put("id", brand.getId());
            brandMap.put("name", brand.getName());
            brandMap.put("slug", brand.getSlug());
            result.put("brand", brandMap);
            Category category = product.getCategory();
            categoryMap.put("id", category.getId());
            categoryMap.put("name", category.getName());
            categoryMap.put("slug", category.getSlug());
            result.put("category", categoryMap);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(result)
                    .build();
        }
        throw new RecordNotFoundException("The product is not exist");
    }

    //TODO Fix: product not deleted and its mainImage record
    @Transactional
    public BaseResponse deleteProduct(Long id){
        Product product = getProduct(id);
        if(product != null){
            //Delete the main image file
            fileStorageService.deleteFile(product.getMainImage().getPath());
            //Delete the product images
            for(ProductImage pi : product.getImages()){
                fileStorageService.deleteFile(pi.getPath());
            }
            productImageRepository.deleteProductImages(product.getId());
            //Delete the product
            repository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Deleted successfully")
                    .withData(null)
                    .build();
        }
        throw new RecordNotFoundException("The product is not exist");
    }
}
