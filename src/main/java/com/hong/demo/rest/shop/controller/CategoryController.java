package com.hong.demo.rest.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hong.demo.rest.shop.service.*;
import com.hong.demo.rest.shop.domain.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> allCategories() throws ServiceException {
        return categoryService.allCategories();
    }

    // @GetMapping("/{categoryId}")
    // public Category getCategory(@PathVariable String categoryId) throws ServiceException {
    //     return categoryService.getCategory(categoryId);
    // }

}

