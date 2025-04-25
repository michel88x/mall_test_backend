package com.michel.mall_test.repository;

import com.michel.mall_test.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category>{

    @Query("SELECT c FROM Category c WHERE c.parentCategory IS NULL")
    List<Category> getParentCategories();

    @Modifying
    @Query("UPDATE Category c SET c.visibility = :v WHERE c.id = :id")
    void updateVisibility(@Param("id") Long id, @Param("v") Boolean v);
}
