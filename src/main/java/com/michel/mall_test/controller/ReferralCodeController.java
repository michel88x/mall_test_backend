package com.michel.mall_test.controller;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.referralCode.ReferralCodeDto;
import com.michel.mall_test.repository.UserRepository;
import com.michel.mall_test.service.ReferralCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/referral-code")
public class ReferralCodeController {

    @Autowired
    private ReferralCodeService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAllReferralCodes());
    }

    @GetMapping("/by-user/{user-id}")
    public ResponseEntity<?> getByUser(@PathVariable("user-id") String userId){
        return ResponseEntity.ok(service.getOneByUser(Long.parseLong(userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneReferralCode(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getReferralCodeById(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> addReferralCode(@ModelAttribute ReferralCodeDto referralCode){
        return ResponseEntity.ok(service.addNewReferralCode(referralCode));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateReferralCode(@PathVariable("id") String id, @ModelAttribute ReferralCodeDto referralCode){
        return ResponseEntity.ok(service.updateReferralCode(referralCode, Long.parseLong(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReferralCode(@PathVariable("id") String id){
        return ResponseEntity.ok(service.deleteOne(Long.parseLong(id)));
    }
}
