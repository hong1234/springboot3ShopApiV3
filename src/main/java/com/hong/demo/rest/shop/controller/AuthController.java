package com.hong.demo.rest.shop.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hong.demo.rest.shop.service.*;
import com.hong.demo.rest.shop.domain.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<SignedInUser> signIn(@RequestBody @Valid LoginDTO signInReq) throws ServiceException {
        CustomerEntity customer = userService.findUserByUsername(signInReq.getUsername());
        if (passwordEncoder.matches(signInReq.getPassword(), customer.getPassword())) {
            return ResponseEntity.ok(userService.createSignedInUser(customer));
        }
        throw new InsufficientAuthenticationException("Unauthorized.");
    }

}