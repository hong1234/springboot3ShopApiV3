package com.hong.demo.rest.shop.domain;

import java.math.BigDecimal;

public record Product(
    // String productId, 
    String id,
    String title, 
    String description,
    String supplier, 
    String searchkeys,
    String image,
    BigDecimal unitPrice
) {}
