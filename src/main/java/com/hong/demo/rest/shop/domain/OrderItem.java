package com.hong.demo.rest.shop.domain;

import java.math.BigDecimal;

public record OrderItem(
    String productId,
    String title,
    BigDecimal unitPrice,
    Integer qty
) {}
