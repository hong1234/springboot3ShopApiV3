package com.hong.demo.rest.shop.domain;

import java.util.UUID;
import java.util.List;

import jakarta.persistence.*; 
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email; 
    private String username;
    private String password;
    // private String role;
    private String authority;

    private String address;
    private String phone;

    // private $customerTelephone;
    // private $customerEmail;
    // private $customerAddress;

    public UserDetails asUser() {
        return User.builder()
        .username(getUsername())
        .password(getPassword())
        .authorities(List.of(new SimpleGrantedAuthority(getAuthority())))
        .build();
    }

}
