package com.hong.demo.rest.shop.domain; 

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty; 

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderEntity { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "items_price")
    private BigDecimal itemsPrice;

    @Column(name = "shipment_price")
    private BigDecimal shipmentPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // private String customerTelephone;
    // private String customerEmail;
    // private String customerAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

}
