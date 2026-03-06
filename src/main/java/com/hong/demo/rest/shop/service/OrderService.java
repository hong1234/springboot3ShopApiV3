package com.hong.demo.rest.shop.service;

import java.util.UUID;
import java.util.List;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.hong.demo.rest.shop.domain.*;
import com.hong.demo.rest.shop.repository.*;

// import jakarta.persistence.PersistenceContext;
// import jakarta.persistence.EntityManager;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.beans.factory.annotation.Autowired;


@RequiredArgsConstructor
// @Transactional
@Service
public class OrderService {

    // private final CartRepository cartRepository;
    // private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    
    // public OrderService(
    //     // CartRepository cartRepository, 
    //     // OrderItemRepository orderItemRepository,
    //     OrderRepository orderRepository
    // ) {
    //     // this.cartRepository = cartRepository;
    //     // this.orderItemRepository = orderItemRepository;
    //     this.orderRepository = orderRepository;
    // }

    public Order getOrderById(String orderId) throws ServiceException {
        OrderEntity order = orderRepository.findById(UUID.fromString(orderId)).get();
        return orderRecord(order);
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(UUID.fromString(customerId))
            .stream().map(order -> orderRecord(order)).toList();
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