package com.michel.mall_test.repository;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.entity.UserVerificationCode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVerificationCodeRepository extends BaseRepository<UserVerificationCode>{
    Optional<UserVerificationCode> findOneByUser(User user);

    Optional<UserVerificationCode> findOneByUserAndCode(User user, String code);

    @Modifying
    @Query("delete from UserVerificationCode a where a.user.id = :id")
    void deleteRow(@Param("id") Long userId);
}
