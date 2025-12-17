package com.fu.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private Long userId;
    private String pickupAddr;
    private String deliveryAddr;
    private BigDecimal shippingFee;
    private List<OrderItemDTO> items;
}
