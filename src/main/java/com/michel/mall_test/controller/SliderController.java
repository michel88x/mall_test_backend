package com.michel.mall_test.controller;

import com.michel.mall_test.extra.dto.slider.SliderDto;
import com.michel.mall_test.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slider")
public class SliderController {

    @Autowired
    private SliderService sliderService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sliderService.getAllSliders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneSlider(@PathVariable("id") String id){
        return ResponseEntity.ok(sliderService.getSlider(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewSlider(@ModelAttribute SliderDto slider){
        return ResponseEntity.ok(sliderService.addNewSlider(slider));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateSlider(@PathVariable("id") String id, @ModelAttribute SliderDto slider){
        return ResponseEntity.ok(sliderService.updateSlider(slider, Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlider(@PathVariable("id") String id){
        return ResponseEntity.ok(sliderService.deleteOne(Long.parseLong(id)));
    }
}
