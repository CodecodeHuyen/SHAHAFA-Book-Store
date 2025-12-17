package com.fu.bookshop.service;

import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.User;

import java.util.List;

public interface OrderService {
    OrderDTO createCheckout(String email, List<Long> cartItemIds);
    Order createOrder(CreateOrderRequest request);
    Order getOrderByCode(String code);
    void updateOrderStatus(Order order, String status);
    void cancelOrder(Order order);
}
