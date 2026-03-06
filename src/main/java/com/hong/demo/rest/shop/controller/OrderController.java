package com.hong.demo.rest.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hong.demo.rest.shop.service.*;
import com.hong.demo.rest.shop.domain.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable String orderId) throws ServiceException {
        return orderService.getOrderById(orderId); 
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable String customerId) throws ServiceException {
        return orderService.getOrdersByCustomerId(customerId); 
    }
}
