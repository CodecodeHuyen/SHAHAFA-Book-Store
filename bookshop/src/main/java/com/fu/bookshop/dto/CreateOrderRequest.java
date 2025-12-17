package com.fu.bookshop.dto;

import com.fu.bookshop.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderRequest {
    private String pickupAddr;
    private String deliveryAddr;
    private BigDecimal shippingFee;
    private List<CartItemDTO> items;
    private Long userId; // ID of the user placing the order
}
