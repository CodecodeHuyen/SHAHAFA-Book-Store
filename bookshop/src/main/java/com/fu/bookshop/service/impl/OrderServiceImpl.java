package com.fu.bookshop.service.impl;

import com.fu.bookshop.entity.Order;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderByCode(String code) {
        return orderRepository.findByCode(code);
    }

    @Override
    public void updateOrderStatus(Order order, String status) {
        order.setOrderStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }
}
