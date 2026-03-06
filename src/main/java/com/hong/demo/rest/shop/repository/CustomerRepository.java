package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import com.hong.demo.rest.shop.domain.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    CustomerEntity findByUsername(String username);
}
