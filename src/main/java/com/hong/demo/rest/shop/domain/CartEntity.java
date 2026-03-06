package com.hong.demo.rest.shop.domain;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

// import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*; 

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

// @Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "carts")
public class CartEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Berlin")
    // @Column(name="updated_on", columnDefinition = "TIMESTAMP") 
    // private LocalDateTime updatedOn;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CustomerEntity customer;

    // @JsonManagedReference
    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    // private List<ProductEntity> products;

    public void addItem(CartItemEntity item) {
        this.items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItemEntity item) {
        item.setCart(null);
        this.items.remove(item);
    }

    // public void removeCartItem(CartItemEntity it) {
    //     it.setCart(null);
    //     // users.removeIf(user -> user.id() == id);
    //     this.items.removeIf(item -> item.getId().toString() == it.getId().toString());
    // }

}
