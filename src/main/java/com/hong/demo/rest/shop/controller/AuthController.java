package com.hong.demo.rest.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hong.demo.rest.shop.service.*;
import com.hong.demo.rest.shop.domain.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public SignedInUser signIn() throws ServiceException {  
        CustomerEntity customer = userService.findUserByUsername("admin");
        return userService.createSignedInUser(customer);
        // if (passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword())) {
        //     return ok(service.getSignedInUser(userEntity));
        // }
        // throw new InsufficientAuthenticationException("Unauthorized.");
    }

    // public ResponseEntity<SignedInUser> signIn(@Valid SignInReq signInReq) {
    //     UserEntity userEntity = service.findUserByUsername(signInReq.getUsername());
    //     if (passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword())) {
    //         return ok(service.getSignedInUser(userEntity));
    //     }
    //     throw new InsufficientAuthenticationException("Unauthorized.");
    // }

}