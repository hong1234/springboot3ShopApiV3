package com.hong.demo.rest.shop.controller;

import java.util.UUID;
import java.util.NoSuchElementException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.hong.demo.rest.shop.config.SecurityConfig;
import com.hong.demo.rest.shop.config.HttpConverterConfig;
import com.hong.demo.rest.shop.domain.CategoryEntity;
import com.hong.demo.rest.shop.domain.ProductEntity;
import com.hong.demo.rest.shop.domain.Product;
import com.hong.demo.rest.shop.domain.ProductDTO;
import com.hong.demo.rest.shop.service.ProductService; 
import com.hong.demo.rest.shop.controller.ProductController;
import com.hong.demo.rest.shop.repository.*;


// @WebMvcTest(ProductController.class)
// @Import(SecurityConfig.class) 
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class ProductControllerTest {

    // @Autowired
    // private WebApplicationContext context; 
    // private MockMvc mockMvc;

    // @BeforeEach
    // public void setup() {
    //     mockMvc = MockMvcBuilders
    //             .webAppContextSetup(context)
    //             .apply(springSecurity())
    //             .build();
    // }

    @Autowired
    private MockMvc mockMvc;

    // @MockBean
    // private ProductService productService; 

    // private Product prod;

    @BeforeEach
    void setup() {

        // CategoryEntity category = CategoryEntity.builder()
        // .id(UUID.fromString("a1b9b31d-e73c-4112-af7c-b68530f38222"))
        // .title("JavaScript")
        // .build();

        // ProductEntity product = ProductEntity.builder()
        // .id(UUID.fromString("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe"))
        // .title("JavaScript today")
        // .description("news in JS")
        // .supplier("Hong Le")
        // .searchkeys("javascript news")
        // .image("/images/Antifragile.jpg")
        // .unitPrice(BigDecimal.valueOf(Double.valueOf("29.99")))
        // .category(category)
        // .build();

        // prod = new Product(
        //     product.getId().toString(), 
        //     product.getTitle(),
        //     product.getDescription(),
        //     product.getSupplier(),
        //     product.getSearchkeys(),
        //     product.getImage(),
        //     product.getUnitPrice()
        // );
    }

    @Test
    // @WithMockUser
    void testCreateProduct() throws Exception {
        // when(productService.addProduct(any(ProductDTO.class))).thenReturn(prod);

        mockMvc
        .perform(post("/api/v1/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                "title": "JavaScript today", 
                "description": "news in JS",
                "supplier": "Hong Le",
                "searchkeys": "javascript news",
                "image": "/images/Antifragile.jpg",
                "unitPrice": "29.99",
                "categoryId": "a1b9b31d-e73c-4112-af7c-b68530f38222"
            }
        """))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("JavaScript today"))
        ;
    }

    // @Test
    // void testGetProductNotFound() throws Exception {
    //     // when(productService.getProduct("510a0d7e-8e83-4193-b483-e27e09ddc33d")).thenThrow(new NoSuchElementException());
    //     mockMvc
    //     .perform(get("/api/v1/products/510a0d7e-8e83-4193-b483-e27e09ddc33d"))
    //     .andDo(print())
    //     .andExpect(status().isNotFound())
    //     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //     // .andExpect(jsonPath("$.message").value("Book not found"))
    //     // .andExpect(jsonPath("$.timestamp").exists())
    //     ;
    // }

    // @Test
    // void testRemoveProduct_Success() throws Exception {
    //     doNothing().when(productService).removeProduct(1);
    //     mockMvc.perform(delete("/api/products/1")
    //        .with(user("admin").roles("ADMIN")))
    //        .andExpect(status().isOk());
    //     verify(productService).removeProduct(1);
    // }
}



