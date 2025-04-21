package com.michel.mall_test.service;

import com.michel.mall_test.entity.Address;
import com.michel.mall_test.entity.City;
import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.address.AddressDto;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityService cityService;

    public BaseResponse getAllAddresses(User user){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(addressRepository.findAllByUser(user))
                .build();
    }

    public Address getOneAddress(Long id){
        return addressRepository.findOne(id);
    }

    public BaseResponse getAddress(Long id){
        Address address = getOneAddress(id);
        if(address != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(address)
                    .build();
        }
        throw new RecordNotFoundException("The address does not exist");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        if(getOneAddress(id) != null) {
            addressRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Address deleted successfully")
                    .withData(null)
                    .build();
        }else{
            throw new RecordNotFoundException("The address does not exist");
        }
    }

    @Transactional
    public BaseResponse addNewAddress(AddressDto dto){
        City city = cityService.getOneCity(dto.getCity());
        if(city != null){
            Address address = addressRepository.save(Address.AddressBuilder.anAddress()
                    .withName(dto.getName())
                    .withCoordinates(dto.getCoordinates())
                    .withStreetName(dto.getStreetName())
                    .withBuildingNumber(dto.getBuildingNumber())
                    .withFloorNumber(dto.getFloorNumber())
                    .withApartmentNumber(dto.getApartmentNumber())
                    .withIsDefault(dto.getDefault())
                    .withNote(dto.getNote())
                    .withUser(dto.getUser())
                    .withCity(city)
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Address added successfully")
                    .withData(address)
                    .build();
        }else{
            throw new RecordNotFoundException("The city does not exist");
        }
    }

    @Transactional
    public BaseResponse updateAddress(AddressDto dto, Long id){
        Address address = getOneAddress(id);
        if(address != null) {
            if(dto.getCity() != null){
                City dtoCity = cityService.getOneCity(dto.getCity());
                if(dtoCity != null){
                    address.setCity(dtoCity);
                } else {
                    throw new RecordNotFoundException("The city does not exist");
                }
            }

            if(dto.getName() != null && !dto.getName().isEmpty()) address.setName(dto.getName());
            if(dto.getCoordinates() != null && !dto.getCoordinates().isEmpty()) address.setCoordinates(dto.getCoordinates());
            if(dto.getStreetName() != null && !dto.getStreetName().isEmpty()) address.setStreetName(dto.getStreetName());
            if(dto.getBuildingNumber() != null) address.setBuildingNumber(dto.getBuildingNumber());
            if(dto.getFloorNumber() != null) address.setFloorNumber(dto.getFloorNumber());
            if(dto.getApartmentNumber() != null) address.setApartmentNumber(dto.getApartmentNumber());
            if(dto.getDefault() != null) address.setDefault(dto.getDefault());
            if(dto.getNote() != null && !dto.getNote().isEmpty()) address.setNote(dto.getNote());

            // No need to call save explicitly if within @Transactional and the entity is managed
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Address updated successfully")
                    .withData(address)
                    .build();
        }else{
            throw new RecordNotFoundException("The address does not exist");
        }
    }

    @Transactional
    public BaseResponse setAddressDefault(Long id){
        if(getOneAddress(id) != null){
        addressRepository.setDefault(id);
        Address address = getOneAddress(id);
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("The address has been set to default one")
                .withData(address)
                .build();
    }else{
            throw new RecordNotFoundException("The address does not exist");
        }
    }
}
