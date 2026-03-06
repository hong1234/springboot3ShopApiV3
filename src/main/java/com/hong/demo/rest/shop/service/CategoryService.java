package com.hong.demo.rest.shop.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hong.demo.rest.shop.domain.Product;
import com.hong.demo.rest.shop.domain.Category;
import com.hong.demo.rest.shop.domain.CategoryEntity;
import com.hong.demo.rest.shop.repository.CategoryRepository;


@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> allCategories(){
        return categoryRepository.findAll()
        .stream().map(cat -> new Category(cat.getId().toString(), cat.getTitle())).toList();
    }
    
    public Category getCategory(String categoryId) throws ServiceException {
        CategoryEntity category = categoryRepository.findById(UUID.fromString(categoryId)).get(); 
        return new Category(
            category.getId().toString(), 
            category.getTitle() // ,
            // category.getProducts().stream().map(prod -> new Product(prod.getTitle(),..)).toList()
        );
    }

}
