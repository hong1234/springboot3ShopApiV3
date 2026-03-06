package com.hong.demo.rest.shop.domain;

import java.util.UUID;
import java.util.List; 
import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import lombok.Builder;

// @AllArgsConstructor
// @Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderitems")
public class OrderItemEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    // @Column(name = "quantity", columnDefinition = "integer default 0")
    private Integer qty; 

    private BigDecimal unitPrice;

    private String title;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    //Hibernate requires no-args constructor
	// public OrderItemEntity(){}
	
	// public OrderItemEntity(Integer qty, BigDecimal unitPrice, String title, ProductEntity product, OrderEntity order){
	// 	this.qty=qty;
	// 	this.unitPrice=unitPrice;
	// 	this.title=title;
    // 	this.product=product;
	// 	this.order=order;
	// }

}
