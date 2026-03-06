package com.hong.demo.rest.shop.domain;

import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.persistence.*; 

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cartitems")
public class CartItemEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer qty;

    private BigDecimal unitPrice;

    // @Column(name="created_at", columnDefinition = "TIMESTAMP") 
    // private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private CartEntity cart;

}
