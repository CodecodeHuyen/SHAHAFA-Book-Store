package com.fu.bookshop.service;

import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDTO createCheckout(String email, List<Long> cartItemIds);
    Order createOrder(CreateOrderRequest request);
    OrderDTO createOrderFromBuyNow(long userId, long bookId, int quantity);
    Order getOrderByCode(String code);
    void updateOrderStatus(Order order, String status);
    void cancelOrder(Order order);
    void processSuccessfulOrder(long orderId);
    Order getOrderById(long orderCode);
    OrderDTO toDTO(Order order);
}
