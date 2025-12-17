package com.fu.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private String bookImage;
    private BigDecimal bookPrice;
    private Integer quantity;
    private Integer availableQuantity; // số lượng có sẵn trong kho
    private BigDecimal subTotal; // quantity * price
}

