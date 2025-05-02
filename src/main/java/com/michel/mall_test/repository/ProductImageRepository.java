package com.michel.mall_test.repository;

import com.michel.mall_test.entity.Product;
import com.michel.mall_test.entity.ProductImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends BaseRepository<ProductImage>{

    @Modifying
    @Query("DELETE FROM ProductImage p WHERE p.manyProduct.id = :id")
    void deleteProductImages(@Param("id") Long id);
}
