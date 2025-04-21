package com.michel.mall_test.service;

import com.michel.mall_test.entity.City;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.city.CityDto;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public BaseResponse getAllCities(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(cityRepository.findAll())
                .build();
    }

    public City getOneCity(Long id){
        return cityRepository.findOne(id);
    }

    public BaseResponse getCity(Long id){
        City city = getOneCity(id);
        if(city != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(city)
                    .build();
        }
        throw new RecordNotFoundException("The city does not exist");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        if(getOneCity(id) != null) {
            cityRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("City deleted successfully")
                    .withData(null)
                    .build();
        }else{
            throw new RecordNotFoundException("The city does not exist");
        }
    }

    @Transactional
    public BaseResponse addNewSlider(CityDto dto){
            City city = cityRepository.save(City.CityBuilder.aCity()
                    .withName(dto.getName())
                            .withSlug(dto.getSlug())
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("City added successfully")
                    .withData(city)
                    .build();
    }

    @Transactional
    public BaseResponse updateCity(CityDto dto, Long id){
        City city = getOneCity(id);
        if(city != null) {
            City newCity = cityRepository.save(City.CityBuilder.aCity()
                    .withId(city.getId())
                    .withName(dto.getName() != null && !dto.getName().isEmpty()? dto.getName() : city.getName())
                    .withSlug(dto.getSlug() != null && !dto.getSlug().isEmpty()? dto.getSlug() : city.getSlug())
                    .build());
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("City updated successfully")
                    .withData(newCity)
                    .build();
        }else{
            throw new RecordNotFoundException("The city does not exist");
        }
    }
}
