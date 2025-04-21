package com.michel.mall_test.repository;

import com.michel.mall_test.entity.Address;
import com.michel.mall_test.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address> {

    public List<Address> findAllByUser(User user);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE address a SET a.is_default = CASE WHEN a.id = :id THEN true ELSE false END")
    public void setDefault(@Param("id") Long id);
}
