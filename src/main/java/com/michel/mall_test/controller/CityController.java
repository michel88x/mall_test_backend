package com.michel.mall_test.controller;

import com.michel.mall_test.extra.dto.city.CityDto;
import com.michel.mall_test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCity(@PathVariable("id") String id){
        return ResponseEntity.ok(cityService.getCity(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> addNewCity(@ModelAttribute CityDto city){
        return ResponseEntity.ok(cityService.addNewSlider(city));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> updateCity(@PathVariable("id") String id, @ModelAttribute CityDto city){
        return ResponseEntity.ok(cityService.updateCity(city, Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") String id){
        return ResponseEntity.ok(cityService.deleteOne(Long.parseLong(id)));
    }
}
