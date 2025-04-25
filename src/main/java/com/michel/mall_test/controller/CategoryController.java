package com.michel.mall_test.controller;

import com.michel.mall_test.extra.dto.brand.BrandDto;
import com.michel.mall_test.extra.dto.category.CategoryDto;
import com.michel.mall_test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getCategory(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewCategory(@ModelAttribute CategoryDto category){
        return ResponseEntity.ok(service.addNewCategory(category));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @ModelAttribute CategoryDto category){
        return ResponseEntity.ok(service.updateCategory(category, Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id){
        return ResponseEntity.ok(service.deleteOne(Long.parseLong(id)));
    }

    @PostMapping(value = "/update-visibility/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> updateVisibility(@PathVariable("id") String id, @RequestParam("visibility") Boolean visibility){
        return ResponseEntity.ok(service.updateVisibility(Long.parseLong(id), visibility));
    }
}
