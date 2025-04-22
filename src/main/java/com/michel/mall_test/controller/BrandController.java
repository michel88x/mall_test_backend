package com.michel.mall_test.controller;

import com.michel.mall_test.extra.dto.brand.BrandDto;
import com.michel.mall_test.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") String id){
        return ResponseEntity.ok(brandService.getBrand(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewBrand(@ModelAttribute BrandDto brand){
        return ResponseEntity.ok(brandService.addNewBrand(brand));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBrand(@PathVariable("id") String id, @ModelAttribute BrandDto brand){
        return ResponseEntity.ok(brandService.updateBrand(brand, Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") String id){
        return ResponseEntity.ok(brandService.deleteOne(Long.parseLong(id)));
    }
}
