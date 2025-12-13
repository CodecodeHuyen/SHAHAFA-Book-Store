package com.fu.bookshop.service;

import com.fu.bookshop.entity.Order;

public interface OrderService {
    Order getOrderByCode(String code);
    void updateOrderStatus(Order order, String status);
}
