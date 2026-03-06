package com.hong.demo.rest.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;

import com.hong.demo.rest.shop.domain.*;
import com.hong.demo.rest.shop.repository.*;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;  

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartRepository cartRepository;
    
    @InjectMocks
    private CartService cartService;

    private CustomerEntity customer;
    private ProductEntity product;
    private ProductEntity newProduct;
    private CartEntity cart;
    
    @BeforeEach
    void setup() {
        
        customer = CustomerEntity.builder().id(UUID.fromString("3395a42e-2d88-40de-b95f-e00e1502085b"))
        .email("test@gmail.com").username("test").password("test").address("test").phone("1234").build();

        CategoryEntity category = CategoryEntity.builder()
        .id(UUID.fromString("a1b9b31d-e73c-4112-af7c-b68530f38222"))
        .title("JavaScript")
        .build();
        
        product = ProductEntity.builder()
        .id(UUID.fromString("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe"))
        .title("JavaScript today")
        .description("news in JS")
        .supplier("Hong Le")
        .searchkeys("javascript news")
        .image("/images/Antifragile.jpg")
        .unitPrice(BigDecimal.valueOf(Double.valueOf("19.99")))
        .category(category)
        .build();

        newProduct = ProductEntity.builder()
        .id(UUID.fromString("892422cd-0835-479e-84de-9265d3f2dc6a"))
        .title("JavaScript for Beginner")
        .description("Js for New commer")
        .supplier("Marx Plank")
        .searchkeys("javascript for beginner")
        .image("/images/Antifragile.jpg")
        .unitPrice(BigDecimal.valueOf(Double.valueOf("29.99")))
        .category(category)
        .build();

        cart = new CartEntity();
        cart.setId(UUID.fromString("6d62d909-f957-430e-8689-b5129c0bb75e"));
        cart.setCustomer(customer);
        // cart.setItems();

        // item#1
        CartItemEntity item = CartItemEntity.builder().id(UUID.fromString("b9f05831-6373-4303-b178-00ba325ca301"))
        .product(product).cart(cart).qty(1).unitPrice(new BigDecimal("29.99")).build();
        // add item#1 to cart
        cart.addItem(item); 
    }

    @Test
    void addOrRemoveCartItem() {
        when(customerRepository.findById(UUID.fromString("3395a42e-2d88-40de-b95f-e00e1502085b"))).thenReturn(Optional.of(customer));
        when(productRepository.findById(UUID.fromString("892422cd-0835-479e-84de-9265d3f2dc6a"))).thenReturn(Optional.of(newProduct));
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

        try {
            CartItemDTO dto = new CartItemDTO();

            // add (new) item#2
            dto.setModus("add");
            dto.setProductId("892422cd-0835-479e-84de-9265d3f2dc6a");
            Cart cartNow = cartService.addItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
            // CartItem item = cartNow.items().get(1);
            assertEquals("JavaScript for Beginner", cartNow.items().get(1).title(), "title should match");
            assertEquals(1, cartNow.items().get(1).qty(), "qty should match");

            // item#1 plus
            dto.setModus("add");
            dto.setProductId("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe");
            cartNow = cartService.addItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
            assertEquals("JavaScript today", cartNow.items().get(0).title(), "title should match");
            assertEquals(2, cartNow.items().get(0).qty(), "qty should match");

            // item#1 minus
            dto.setModus("remove");
            dto.setProductId("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe");
            cartNow = cartService.removeItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
            assertEquals("JavaScript today", cartNow.items().get(0).title(), "title should match");
            assertEquals(1, cartNow.items().get(0).qty(), "qty should match");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // @Test
    // void addNewCartItem() {
    //     when(customerRepository.findById(UUID.fromString("3395a42e-2d88-40de-b95f-e00e1502085b"))).thenReturn(Optional.of(customer));
    //     when(productRepository.findById(UUID.fromString("892422cd-0835-479e-84de-9265d3f2dc6a"))).thenReturn(Optional.of(newProduct));
    //     when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

    //     CartItemDTO dto = new CartItemDTO();
    //     dto.setProductId("892422cd-0835-479e-84de-9265d3f2dc6a");
    //     dto.setModus("add");

    //     try {
    //         Cart cartNow = cartService.addItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
    //         CartItem item = cartNow.items().get(1);
    //         assertEquals("JavaScript for Beginner", item.title(), "title should match");
    //         assertEquals(1, item.qty(), "qty should match");
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }

    // @Test
    // void addOneCartItem() {
    //     when(customerRepository.findById(UUID.fromString("3395a42e-2d88-40de-b95f-e00e1502085b"))).thenReturn(Optional.of(customer));
    //     when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

    //     CartItemDTO dto = new CartItemDTO();
    //     dto.setProductId("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe");
    //     dto.setModus("add");

    //     try {
    //         Cart cartNow = cartService.addItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
    //         assertEquals(2, cartNow.items().get(0).qty(), "qty should match");
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }

    // @Test
    // void removeOneCartItem() {
    //     when(customerRepository.findById(UUID.fromString("3395a42e-2d88-40de-b95f-e00e1502085b"))).thenReturn(Optional.of(customer));
    //     when(cartRepository.save(any(CartEntity.class))).thenReturn(cart);

    //     CartItemDTO dto = new CartItemDTO();
    //     dto.setProductId("ca3d3d42-9379-4ba4-bf3e-a09ec3efbabe");
    //     dto.setModus("remove");

    //     try {
    //         Cart cartNow = cartService.removeItem("3395a42e-2d88-40de-b95f-e00e1502085b", dto);
    //         assertEquals(1, cartNow.items().get(0).qty(), "qty should match");
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }

    // }

}
