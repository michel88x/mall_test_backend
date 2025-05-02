package com.michel.mall_test.controller;

import com.michel.mall_test.extra.dto.product.AddProductDto;
import com.michel.mall_test.extra.dto.slider.SliderDto;
import com.michel.mall_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewProduct(@ModelAttribute AddProductDto product){
        return ResponseEntity.ok(productService.addNewProduct(product));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(@ModelAttribute AddProductDto product, @PathVariable("id") String id){
        return ResponseEntity.ok(productService.updateProduct(Long.parseLong(id), product));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable("id") String id){
        return ResponseEntity.ok(productService.getProductDetails(Long.parseLong(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id){
        return ResponseEntity.ok(productService.deleteProduct(Long.parseLong(id)));
    }
}
