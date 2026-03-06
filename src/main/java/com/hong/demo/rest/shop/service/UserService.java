package com.hong.demo.rest.shop.service;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// import org.springframework.security.core.userdetails.User;

import com.hong.demo.rest.shop.config.JwtManager;

import com.hong.demo.rest.shop.domain.*;
import com.hong.demo.rest.shop.repository.*;


// @Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final CustomerRepository customerRepository;
    private final JwtManager tokenManager;

    public CustomerEntity findUserByUsername(String name){
        return customerRepository.findByUsername(name);
    }

    public SignedInUser createSignedInUser(CustomerEntity customer) {
	    String token = tokenManager.create(customer.asUser());
	    return new SignedInUser(
            customer.getId().toString(),
            token
        );
    }
}

