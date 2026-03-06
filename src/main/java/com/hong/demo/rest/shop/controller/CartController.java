package com.hong.demo.rest.shop.controller;

import java.util.List;
import java.util.ArrayList;
import jakarta.validation.Valid;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

import com.hong.demo.rest.shop.service.*;
import com.hong.demo.rest.shop.domain.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/{customerId}/items")
    public ResponseEntity<Cart> AddOrRemoveItem (
        @PathVariable String customerId, 
        @Valid @RequestBody CartItemDTO item
    ) throws ServiceException {
        String action = item.getModus();
        if(action.equals("add")){
            Cart newItem = cartService.addItem(customerId, item);
            return ResponseEntity.ok(newItem);
        } else {
            Cart newItem = cartService.removeItem(customerId, item);
            return ResponseEntity.ok(newItem);
        }
    }

    @GetMapping("/{customerId}")
    public Cart getCartByCustomerId(@PathVariable String customerId) throws ServiceException {
        return cartService.getCartByCustomerId(customerId); 
    }

    @GetMapping("/{customerId}/checkout")
    public Order cartCheckout(@PathVariable String customerId) throws ServiceException {
        return cartService.cartCheckOut(customerId); 
    }

    // @GetMapping("/{customerId}/test")
    // public Cart getTestCartById(@PathVariable String customerId) throws ServiceException {
    //     return cartService.getCartItemsByCustomerIdId(customerId); 
    // }

    // @GetMapping("/{cartId}/info")
    // public Cart getCartById(@PathVariable String cartId) throws ServiceException {
    //     return cartService.getCartByCartId(cartId); 
    // }

    // @PutMapping("/{customerId}/items")
    // public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item) {
    //     return ok(service.addOrReplaceItemsByCustomerId(customerId, item));
    // }

    // @PostMapping("/{customerId}/items")
    // @PutMapping("/{customerId}/items")
    // public ResponseEntity<CartItem> AddOrRemoveItem(@PathVariable String customerId, @Valid @RequestBody CartItem item) {
    //     // return ResponseEntity.ok(item.modus());
    //     return ResponseEntity.ok(item);
    // }

    // @PutMapping("/{id}")
    // public User updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
    //     User newUser = new User(id, updatedUser.name());
    //     return newUser;
    //     // return null;
    // }

    // @DeleteMapping("/{id}")
    // public void deleteUser(@PathVariable int id) {
    //     users.removeIf(user -> user.id() == id);
    // }

}