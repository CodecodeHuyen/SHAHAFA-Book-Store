package com.fu.bookshop.controller;

import com.fu.bookshop.dto.CartItemDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.service.OrderService;
import com.fu.bookshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/create-from-cart")
    public String createOrderFromCart(@ModelAttribute CreateOrderRequest request) {
        try {
            // In a real application, you would get the user ID from the session
            long userId = 1L;
            BigDecimal shippingFee = new BigDecimal("30000");
            String pickupAddr = "123 Pickup St, City";
            String deliveryAddr = "456 Delivery St, City";

            request.setUserId(userId);
            request.setShippingFee(shippingFee);
            request.setPickupAddr(pickupAddr);
            request.setDeliveryAddr(deliveryAddr);

            // Filter out items that were not selected
            List<CartItemDTO> selectedItems = request.getItems().stream()
                    .filter(item -> item.getBookId() != null)
                    .collect(Collectors.toList());
            request.setItems(selectedItems);

            Order order = orderService.createOrder(request);
            int amount = order.getTotalPrice().add(order.getShippingFee()).intValue();
            String description = "Thanh toan don hang " + order.getCode();
            String paymentUrl = paymentService.createPaymentLink(order.getId(), amount, description);

            return "redirect:" + paymentUrl;
        } catch (Exception e) {
            e.printStackTrace();
            // You should redirect to an error page
            return "redirect:/error";
        }
    }

    @PostMapping("/create-and-pay")
    public String createOrderAndPay(@RequestParam long bookId, @RequestParam int quantity) {
        try {
            // In a real application, you would get the user ID from the session
            long userId = 1L;
            BigDecimal shippingFee = new BigDecimal("30000");
            String pickupAddr = "123 Pickup St, City";
            String deliveryAddr = "456 Delivery St, City";

            CartItemDTO item = new CartItemDTO();
            item.setBookId(bookId);
            item.setQuantity(quantity);

            CreateOrderRequest request = new CreateOrderRequest();
            request.setUserId(userId);
            request.setShippingFee(shippingFee);
            request.setPickupAddr(pickupAddr);
            request.setDeliveryAddr(deliveryAddr);
            request.setItems(Collections.singletonList(item));

            Order order = orderService.createOrder(request);
            int amount = order.getTotalPrice().add(order.getShippingFee()).intValue();
            String description = "Thanh toan don hang " + order.getCode();
            String paymentUrl = paymentService.createPaymentLink(order.getId(), amount, description);

            return "redirect:" + paymentUrl;
        } catch (Exception e) {
            e.printStackTrace();
            // You should redirect to an error page
            return "redirect:/error";
        }
    }

    @PostMapping("/create")
    public String createOrderAndGetPaymentLink(@RequestBody CreateOrderRequest request) {
        try {
            Order order = orderService.createOrder(request);
            int amount = order.getTotalPrice().add(order.getShippingFee()).intValue();
            String description = "Thanh toan don hang " + order.getCode();
            return paymentService.createPaymentLink(order.getId(), amount, description);
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi: " + e.getMessage();
        }
    }
}
