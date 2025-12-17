package com.fu.bookshop.controller;

import com.fu.bookshop.entity.Order;
import com.fu.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentRedirectController {

    private final OrderService orderService;

    @Autowired
    public PaymentRedirectController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam("orderCode") long orderCode) {
        Order order = orderService.getOrderById(orderCode);
        if (order != null) {
            orderService.processSuccessfulOrder(order.getId());
        }
        return "success";
    }

    @GetMapping("/cancel")
    public String paymentCancel(@RequestParam("orderCode") long orderCode) {
        Order order = orderService.getOrderById(orderCode);
        if (order != null) {
            orderService.updateOrderStatus(order, "CANCELLED");
        }
        return "cancel";
    }
}
