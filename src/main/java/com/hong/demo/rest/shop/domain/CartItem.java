package com.hong.demo.rest.shop.domain;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.validation.constraints.*; 

public record CartItem(
    // String itemId,
    @NotBlank(message = "productId is required.") String productId,
    String title,
    BigDecimal unitPrice,
    @NotNull(message = "quantity is required.") Integer qty
) {}
