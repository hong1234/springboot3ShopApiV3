package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hong.demo.rest.shop.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("from ProductEntity p where p.searchkeys like %?1%")
    List<ProductEntity> searchByTitle(String title);

    @Query("SELECT p FROM ProductEntity p WHERE p.category.id = :categoryId")
    List<ProductEntity> productsOfCategoryId(@Param("categoryId") UUID categoryId);
}
