package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hong.demo.rest.shop.domain.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query("SELECT o FROM OrderEntity o WHERE o.customer.id = :customerId ORDER BY o.createdAt DESC")
    List<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
