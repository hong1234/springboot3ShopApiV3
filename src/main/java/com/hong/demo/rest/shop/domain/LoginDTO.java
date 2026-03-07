package com.hong.demo.rest.shop.domain;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class LoginDTO {
    @NotBlank(message = "username is required.")
    private String username; 

    @NotBlank(message = "password is required.")
    private String password;
}
