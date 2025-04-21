package com.michel.mall_test.service;

import com.michel.mall_test.entity.ReferralCode;
import com.michel.mall_test.entity.User;
import com.michel.mall_test.extra.dto.BaseResponse;
import com.michel.mall_test.extra.dto.referralCode.ReferralCodeDto;
import com.michel.mall_test.extra.exceptions.RecordNotFoundException;
import com.michel.mall_test.repository.ReferralCodeRepository;
import com.michel.mall_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReferralCodeService {

    @Autowired
    private ReferralCodeRepository referralRepository;

    @Autowired
    private UserRepository userRepository;

    public BaseResponse getAllReferralCodes(){
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Got successfully")
                .withData(referralRepository.findAll())
                .build();
    }

    public BaseResponse getOneByUser(Long userId){
        User user = userRepository.findOne(userId);
        if(user != null){
            Optional<ReferralCode> code = referralRepository.findOneByUser(user);
            if(code.isPresent()){
                return BaseResponse.BaseResponseBuilder.aBaseResponse()
                        .withSuccess(true)
                        .withMessage("Got successfully")
                        .withData(code.get())
                        .build();
            }else{
                throw new RecordNotFoundException("There is no referral code for this user");
            }
        }else{
            throw new RecordNotFoundException("The user does not found");
        }
    }

    public ReferralCode getOneReferralCode(Long id){
        return referralRepository.findOne(id);
    }

    public BaseResponse getReferralCodeById(Long id){
        ReferralCode code = getOneReferralCode(id);
        if(code != null){
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Got successfully")
                    .withData(code)
                    .build();
        }
        throw new RecordNotFoundException("The referral code does not found");
    }

    @Transactional
    public BaseResponse deleteOne(Long id){
        ReferralCode code = getOneReferralCode(id);
        if (code != null){
            referralRepository.delete(id);
            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Referral code deleted successfully")
                    .withData(null)
                    .build();
        }
        throw new RecordNotFoundException("The referral code does not found");
    }

    public BaseResponse addNewReferralCode(ReferralCodeDto dto){
        User user = userRepository.findOne(dto.getUser());
        if(user == null){
            throw new RecordNotFoundException("The user does not exist");
        }
        ReferralCode referralCode = referralRepository.save(ReferralCode.ReferralCodeBuilder.aReferralCode()
                        .withCode(dto.getCode())
                        .withValue(dto.getValue())
                        .withUser(user)
                .build());
        return BaseResponse.BaseResponseBuilder.aBaseResponse()
                .withSuccess(true)
                .withMessage("Created successfully")
                .withData(referralCode)
                .build();
    }

    @Transactional
    public BaseResponse updateReferralCode(ReferralCodeDto dto, Long id){
        ReferralCode referralCode = getOneReferralCode(id);
        if(referralCode != null){
            if(dto.getUser() != null){
                User user = userRepository.findOne(dto.getUser());
                if(user != null){
                    referralCode.setUser(user);
                }
                throw new RecordNotFoundException("The user does not exist");
            }
            if(dto.getCode() != null && !dto.getCode().isEmpty()) referralCode.setCode(dto.getCode());
            if(dto.getValue() != null) referralCode.setValue(dto.getValue());

            return BaseResponse.BaseResponseBuilder.aBaseResponse()
                    .withSuccess(true)
                    .withMessage("Referral code updated successfully")
                    .build();
        }
        throw new RecordNotFoundException("The referral code does not exist");
    }
}
