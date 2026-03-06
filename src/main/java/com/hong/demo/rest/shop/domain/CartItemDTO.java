package com.hong.demo.rest.shop.domain;

// import java.util.Date;
// import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.*;

// import com.hong.demo.validation.EnumNamePattern;
// import com.hong.demo.validation.StatusValidation;
// import com.fasterxml.jackson.annotation.JsonFormat;

@NoArgsConstructor
@Data
public class CartItemDTO {
    // @Email(message = "Please provide a valid email address.")
    // @EmailRegistered(message = "Email is not registered.")
    // private String email;
    @NotNull(message = "Modus is required.")
    @Pattern(regexp = "^(add|remove)$", message = "modus must be 'add' or 'remove'")
    private String modus;

    // @Pattern(regexp = "^[0-9]", message = "Invalid quantity") 
    // @NotNull(message = "quantity is required.")
    // private Integer qty;

    // private BigDecimal unitPrice;

    @NotBlank(message = "ProductID is required.")
    private String productId; 

    // String productTitle

    // public CartItemEntity asCartItem(){
    //     CartItemEntity item = new CartItemEntity();
    //     // item.setName(getName());
    //     // item.setEmail(getEmail());
    //     // item.setContent(getContent());
    //     // item.setLikeStatus(LikeStatus.valueOf(getLikeStatus()));
    //     return item;
    // }
}
