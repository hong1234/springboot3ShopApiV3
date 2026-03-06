package com.hong.demo.rest.shop.service;

import java.util.Objects;
import java.util.UUID;
import java.util.List;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.hong.demo.rest.shop.domain.*;
import com.hong.demo.rest.shop.repository.*;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
// @Transactional
@Service
public class CartService {

    // @PersistenceContext private final EntityManager em;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public Cart getCartByCustomerId(String customerId) throws ServiceException {
        CartEntity cart = getCartEntityByCustomerId(customerId);
        return cartRecord(cart);
    }

    @Transactional
    public Cart removeItem(String customerId, CartItemDTO dto) throws ServiceException {

        CartItemEntity item = new CartItemEntity();
        CartEntity cart = getCartEntityByCustomerId(customerId);
        // List<CartItemEntity> items = Objects.nonNull(cart.getItems()) ? cart.getItems() : List.of();
        // for (CartItemEntity i : items) {}

        for (CartItemEntity i : cart.getItems()) {
            if (i.getProduct().getId().equals(UUID.fromString(dto.getProductId()))) {
                item = i;
                // if(i.getQty().equals(1)) {
                //     cartItemRepository.deleteById(i.getId());
                // } else {
                //     i.setQty(i.getQty() - 1);
                //     cartItemRepository.save(i);
                // } 
            }
        }

        if(item.getQty().equals(1)) {
            cart.removeItem(item);
        } else {
            item.setQty(item.getQty() - 1);
        }

        cart = cartRepository.save(cart);
        return cartRecord(cart);
    }

    @Transactional
    public Cart addItem(String customerId, CartItemDTO dto) throws ServiceException {

        boolean needNew = true;
        CartEntity cart = getCartEntityByCustomerId(customerId);

        for (CartItemEntity i : cart.getItems()) {
            if (i.getProduct().getId().equals(UUID.fromString(dto.getProductId()))) {
                i.setQty(i.getQty() + 1);
                // cartItemRepository.save(i); 
                needNew = false; 
            }
        }

        if(needNew) { // new Item
            ProductEntity prod = getProductById(dto.getProductId()); 

            CartItemEntity newItem = new CartItemEntity();
            newItem.setQty(1);
            newItem.setUnitPrice(prod.getUnitPrice());
            newItem.setProduct(prod);

            // newItem.setCart(cart);
            // cart.getItems().add(newItem);
            cart.addItem(newItem);

            // cartItemRepository.save(newItem);
        }

        cart = cartRepository.save(cart);
        return cartRecord(cart);
    }

    @Transactional
    public Order cartCheckOut(String customerId) throws ServiceException {
        CartEntity cart = getCartEntityByCustomerId(customerId);

        OrderEntity order = new OrderEntity();
        order.setCreatedAt(LocalDateTime.now());
        order.setShipmentPrice(new BigDecimal("5.99"));
        order.setCustomer(cart.getCustomer());

        BigDecimal total = BigDecimal.ZERO;
        OrderItemEntity temp;

        for (CartItemEntity item : cart.getItems()) {
            total = (BigDecimal.valueOf(item.getQty()).multiply(item.getUnitPrice())).add(total);

            temp = new OrderItemEntity();
            temp.setQty(item.getQty());
            temp.setUnitPrice(item.getUnitPrice());
            temp.setTitle(item.getProduct().getTitle());
            temp.setProduct(item.getProduct());
            temp.setOrder(order);

            order.getItems().add(temp);
        }

        order.setItemsPrice(total);
        total = (BigDecimal.valueOf(Double.valueOf("5.99"))).add(total);
        order.setTotalPrice(total);

        order = orderRepository.save(order);
        
        // cartRepository.delete(cart);
        cartRepository.deleteById(cart.getId());

        return orderRecord(order);
    }

    public Cart getCartByCartId(String cartId) throws ServiceException {
        CartEntity cart = cartRepository.findById(UUID.fromString(cartId)).get();
        return cartRecord(cart);
    }

    public CartEntity getCartEntityByCustomerId(String customerId) {
        // CartEntity cart = cartRepository.findByCustomerId(UUID.fromString(customerId)).get();
        CartEntity cart = cartRepository.findByCustomerId(UUID.fromString(customerId)).orElse(new CartEntity());

         if (cart.getId()==null) {
            // CustomerEntity customer = customerRepository.findById(UUID.fromString(customerId)).orElse(null);
            cart.setCustomer(customerRepository.findById(UUID.fromString(customerId)).get());
            // cart.setId(UUID.randomUUID());
            cart = cartRepository.save(cart);
         }

        return cart;
    }

    public ProductEntity getProductById(String productId) {
        ProductEntity prod = productRepository.findById(UUID.fromString(productId)).get();
        return prod;
    }

    public Cart cartRecord(CartEntity cart) {
        return new Cart(
            cart.getId().toString(),
            cart.getItems().stream().map(
                item -> new CartItem(
                    // item.getId().toString(), // test
                    item.getProduct().getId().toString(),
                    item.getProduct().getTitle(),
                    item.getUnitPrice(),
                    item.getQty()
                )
            ).toList()
        );
    }

    public Order orderRecord(OrderEntity order) { 
        return new Order(
            order.getId().toString(),
            order.getCreatedAt(),
            order.getShipmentPrice(),
            order.getTotalPrice(), 
            order.getCustomer().getAddress(),
            order.getItems().stream().map(
                item -> new OrderItem(
                    item.getProduct().getId().toString(),
                    item.getProduct().getTitle(),
                    item.getUnitPrice(),
                    item.getQty()
                )
            ).toList()
        );
    }

}