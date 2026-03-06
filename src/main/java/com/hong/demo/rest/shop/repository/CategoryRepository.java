package com.hong.demo.rest.shop.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hong.demo.rest.shop.domain.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    
}

