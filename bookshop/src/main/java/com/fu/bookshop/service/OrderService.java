package com.fu.bookshop.service;

import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.entity.Order;

public interface OrderService {
    Order createOrder(CreateOrderRequest request);
    Order getOrderByCode(String code);
    void updateOrderStatus(Order order, String status);
    void cancelOrder(Order order);
}
