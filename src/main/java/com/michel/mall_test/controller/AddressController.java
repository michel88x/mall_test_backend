package com.michel.mall_test.controller;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.address.AddressDto;
import com.michel.mall_test.extra.exceptions.UserNotFoundException;
import com.michel.mall_test.service.AddressService;
import com.michel.mall_test.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String authHeader){
        Optional<User> user = jwtService.getUserByToken(authHeader);
        if(user.isPresent()) {
            return ResponseEntity.ok(addressService.getAllAddresses(user.get()));
        }else{
            throw new UserNotFoundException("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAddress(@PathVariable("id") String id){
        return ResponseEntity.ok(addressService.getAddress(Long.parseLong(id)));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> addNewAddress(@ModelAttribute AddressDto address, @RequestHeader("Authorization") String authHeader){
        Optional<User> user = jwtService.getUserByToken(authHeader);
        if(user.isPresent()) {
            address.setUser(user.get());
            return ResponseEntity.ok(addressService.addNewAddress(address));
        }else{
            throw new UserNotFoundException("");
        }
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> updateAddress(@PathVariable("id") String id, @ModelAttribute AddressDto address, @RequestHeader("Authorization") String authHeader){
        Optional<User> user = jwtService.getUserByToken(authHeader);
        if(user.isPresent()) {
            address.setUser(user.get());
            return ResponseEntity.ok(addressService.updateAddress(address, Long.parseLong(id)));
        }else{
            throw new UserNotFoundException("");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") String id){
        return ResponseEntity.ok(addressService.deleteOne(Long.parseLong(id)));
    }

    @PostMapping("/set-default/{id}")
    public ResponseEntity<?> setDefault(@PathVariable("id") String id){
        return ResponseEntity.ok(addressService.setAddressDefault(Long.parseLong(id)));
    }
}
