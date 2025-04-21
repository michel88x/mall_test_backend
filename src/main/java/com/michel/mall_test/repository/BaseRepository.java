package com.michel.mall_test.repository;

import com.michel.mall_test.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public abstract interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    public default T findOne(Long id) {
        return findById(id)
                .orElse(null);
    }

    public default List<T> findAll(Iterable<Long> ids) {
        return findAllById(ids);
    }

    public default boolean exists(Long id) {
        return existsById(id);
    }

    public default void delete(Iterable<? extends T> entities) {
        deleteAll(entities);
    }

    public default void delete(Long id) {
        deleteById(id);
    }

    public default <S extends T> List<S> save(Iterable<S> entities) {
        return saveAll(entities);
    }
}
