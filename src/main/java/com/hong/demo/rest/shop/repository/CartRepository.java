package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hong.demo.rest.shop.domain.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, UUID> { 
    @Query("SELECT c FROM CartEntity c JOIN c.customer u WHERE u.id = :customerId")
    public Optional<CartEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
