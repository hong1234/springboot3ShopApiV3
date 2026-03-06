package com.hong.demo.rest.shop.domain;

import java.util.List;

public record Cart(
    String cartId, 
    List<CartItem> items
) {}
