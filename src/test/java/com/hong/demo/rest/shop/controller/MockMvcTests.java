package com.hong.demo.rest.shop.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.security.test.context.support.WithMockUser;


@SpringBootTest
@AutoConfigureMockMvc
// @ActiveProfiles("mockMvc")
public class MockMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
       
    }

    @Test
    // @WithMockUser(username = "admin2", password = "admin", roles = "ADMIN")
    // @WithMockUser(username = "admin2", roles = {"ADMIN"})
    @WithMockUser
    void testGetProduct() throws Exception {
        mockMvc
        .perform(get("/api/v1/products/510a0d7e-8e83-4193-b483-e27e09ddc34d"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // .andExpect(content().string(containsString("JavaScript for Gurus")))
        // .andExpect(jsonPath("$.title").value(product.getTitle()))
        .andExpect(jsonPath("$.title").value("JavaScript for Gurus"))
        .andExpect(jsonPath("$.description").value("book for Gurus"))
        ;
    }

}

 
    


    
