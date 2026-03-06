package com.hong.demo.rest.shop.domain;

import lombok.Data;
import java.math.BigDecimal;

import jakarta.validation.constraints.*;

@Data
public class ProductDTO {
    @NotBlank(message = "title is required.")
    private String title; 

    @NotBlank(message = "description is required.")
    private String description;

    @NotBlank(message = "supplier is required.")
    private String supplier;

    @NotBlank(message = "searchkeys is required.")
    private String searchkeys;

    @NotBlank(message = "image is required.")
    private String image;

    @Digits(integer=6, fraction=2)
    @NotNull(message = "price is required.")
    private BigDecimal unitPrice;

    @NotBlank(message = "category is required.")
    private String categoryId;

    // @Pattern(regexp = "^d+.d+$", message = "price is invalid format")
    // @NotBlank(message = "price is required.")
    // private String unitPrice;
}
