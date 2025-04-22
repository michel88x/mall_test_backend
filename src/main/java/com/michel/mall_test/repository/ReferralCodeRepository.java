package com.michel.mall_test.repository;

import com.michel.mall_test.entity.ReferralCode;
import com.michel.mall_test.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReferralCodeRepository extends BaseRepository<ReferralCode>{

    @Query("SELECT u FROM User u INNER JOIN ReferralCode r ON u.id = r.user.id WHERE r.code = :code")
    Optional<User> findUserByCode(String code);

    Optional<ReferralCode> findOneByUser(User user);

    Optional<ReferralCode> findOneByCode(String code);
}
