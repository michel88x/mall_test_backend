package com.michel.mall_test.repository;

import com.michel.mall_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id") Long id);

    @Modifying
    @Query("update User u set u.emailVerified = true where u.id = :id")
    void updateUserEmailVerified(@Param("id") Long id);
}
