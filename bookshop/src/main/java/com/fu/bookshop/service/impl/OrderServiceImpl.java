package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.CartItemDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.entity.Book;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setPickupAddr(request.getPickupAddr());
        order.setDeliveryAddr(request.getDeliveryAddr());
        order.setShippingFee(request.getShippingFee());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setDiscount(BigDecimal.ZERO); // You can add discount logic later

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItemDTO itemDTO : request.getItems()) {
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(book.getPrice());
            orderItems.add(orderItem);

            totalPrice = totalPrice.add(book.getPrice().multiply(new BigDecimal(itemDTO.getQuantity())));
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByCode(String code) {
        return orderRepository.findByCode(code);
    }

    @Override
    public void updateOrderStatus(Order order, String status) {
        order.setOrderStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
