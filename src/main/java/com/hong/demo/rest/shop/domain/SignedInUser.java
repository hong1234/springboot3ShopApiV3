package com.hong.demo.rest.shop.domain;

public record SignedInUser(
    String userId, 
    String token
) {} 