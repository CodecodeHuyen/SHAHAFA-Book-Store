package com.fu.bookshop.dto.user;

import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderHistoryDTO {

    private Long orderId;
    private String orderCode;

    private OrderStatus orderStatus;

    private BigDecimal totalPrice;
    private BigDecimal discount;
    private BigDecimal shippingFee;
    private String pickupAddr;
    private String deliveryAddr;

    private PaymentStatus paymentStatus;
    private String paymentMethodName;
    private String paymentMethodImage;

    private List<OrderItemDTO> items;

    public boolean isCompleted(){
        return orderStatus == OrderStatus.COMPLETE;
    }

    public boolean isCancelled() {
        return orderStatus == OrderStatus.CANCELLED;
    }

}
