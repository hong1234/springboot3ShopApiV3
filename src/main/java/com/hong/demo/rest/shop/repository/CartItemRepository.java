package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import com.hong.demo.rest.shop.domain.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    @Modifying
    @Query("delete from CartItemEntity c where c.id = :id")
    void deleteById(UUID id);
}
