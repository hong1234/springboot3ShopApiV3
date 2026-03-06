package com.hong.demo.rest.shop.domain;

// import java.util.UUID;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Order(
    String orderId, 
    LocalDateTime createdAt, 
    // BigDecimal itemsPrice,
    BigDecimal shipmentPrice,
    BigDecimal totalPrice,
    // String customerId,
    String customerAddress,
    List<OrderItem> items
) {}
