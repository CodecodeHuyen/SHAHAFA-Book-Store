package com.fu.bookshop.dto;

import com.fu.bookshop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String code;
    private OrderStatus orderStatus;

    private BigDecimal subTotal;     // tổng tiền hàng
    private BigDecimal shippingFee;  // có thể null lúc mới checkout
    private BigDecimal discount;     // có thể null
    private BigDecimal total;        // subTotal - discount + shippingFee

    private List<OrderItemDTO> items;
    private int totalWeight;
}
