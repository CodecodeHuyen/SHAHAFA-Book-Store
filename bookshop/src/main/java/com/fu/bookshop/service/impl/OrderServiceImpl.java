package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.CartItemDTO;
import com.fu.bookshop.dto.CreateOrderRequest;
import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.OrderItemDTO;
import com.fu.bookshop.entity.*;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.BookRepository;
import com.fu.bookshop.repository.CartItemRepository;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void processSuccessfulOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));

        // Update order status
        order.setOrderStatus(OrderStatus.COMPLETE);
        orderRepository.save(order);

        // Update book quantities and remove from cart if item exists
        for (OrderItem item : order.getOrderItems()) {
            Book book = item.getBook();
            int newQuantity = book.getQuantity() - item.getQuantity();
            book.setQuantity(newQuantity);
            bookRepository.save(book);

            // Check if the item exists in the cart before deleting
            Optional<CartItem> cartItem = cartItemRepository.findByCart_UserAndBook(order.getUser(), book);
            cartItem.ifPresent(cartItemRepository::delete);
        }
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .user(user)
                .pickupAddr(request.getPickupAddr())
                .deliveryAddr(request.getDeliveryAddr())
                .shippingFee(request.getShippingFee())
                .orderStatus(OrderStatus.PENDING)
                .discount(BigDecimal.ZERO)
                .totalPrice(BigDecimal.ZERO) // Set default value
                .build();

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItemDTO itemDTO : request.getItems()) {
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .book(book)
                    .quantity(itemDTO.getQuantity())
                    .unitPrice(book.getPrice())
                    .build();
            orderItems.add(orderItem);

            totalPrice = totalPrice.add(book.getPrice().multiply(new BigDecimal(itemDTO.getQuantity())));
        }

        savedOrder.setOrderItems(orderItems);
        savedOrder.setTotalPrice(totalPrice);

        return orderRepository.save(savedOrder);
    }

    @Override
    public OrderDTO createOrderFromBuyNow(long userId, long bookId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Order order = Order.builder()
                .user(user)
                .orderStatus(OrderStatus.PENDING)
                .totalPrice(book.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .discount(BigDecimal.ZERO)
                .shippingFee(BigDecimal.ZERO)
                .build();

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .book(book)
                .quantity(quantity)
                .unitPrice(book.getPrice())
                .build();

        order.setOrderItems(Collections.singletonList(orderItem));
        Order savedOrder = orderRepository.save(order);
        return toDTO(savedOrder);
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


    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDTO createCheckout(String email, List<Long> cartItemIds) {

        List<CartItem> cartItems = cartItemRepository.findAllById(cartItemIds);

        if (cartItems.isEmpty()) {
            throw new BusinessException(ErrorCode.CART_EMPTY);
        }

        User user = userRepository.findByAccount_Email(email).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 1) Tạo Order entity
        Order order = Order.builder()
                .user(user)
                .orderStatus(OrderStatus.PENDING)
                .totalPrice(BigDecimal.ZERO)
                .discount(BigDecimal.ZERO)
                .shippingFee(BigDecimal.ZERO)
                .build();

        // 2) Convert CartItem -> OrderItem entity
        List<OrderItem> orderItems = cartItems.stream()
                .map(ci -> OrderItem.builder()
                        .order(order)
                        .book(ci.getBook())
                        .quantity(ci.getQuantity())
                        .unitPrice(ci.getBook().getPrice())
                        .build())
                .toList();

        // 3) Tính subTotal
        BigDecimal subTotal = orderItems.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(subTotal);
        order.setOrderItems(orderItems);

        // 5) Save
        Order saved = orderRepository.save(order);

        // 6) Map -> DTO để trả ra controller/view
        return toDTO(saved);
    }



    public OrderDTO toDTO(Order order) {
        List<OrderItemDTO> items = order.getOrderItems().stream()
                .map(oi -> OrderItemDTO.builder()
                        .id(oi.getId())
                        .bookId(oi.getBook().getId())
                        .bookTitle(oi.getBook().getTitle())
                        .bookImage(oi.getBook().getUrlImage())
                        .quantity(oi.getQuantity())
                        .unitPrice(oi.getUnitPrice())
                        .build())
                .toList();

        BigDecimal discount = nvl(order.getDiscount());
        BigDecimal shippingFee = nvl(order.getShippingFee());
        BigDecimal subTotal = nvl(order.getTotalPrice());
        BigDecimal total = subTotal.subtract(discount).add(shippingFee);

        // Tính tổng trọng lượng từ các item
        int totalWeight = order.getOrderItems().stream()
                .mapToInt(i -> i.getBook().getWeight() * i.getQuantity())  // lấy weight và quantity
                .sum();

        // Set totalWeight vào DTO
        return OrderDTO.builder()
                .id(order.getId())
                .code(order.getCode())
                .orderStatus(order.getOrderStatus())
                .subTotal(subTotal)
                .discount(discount)
                .shippingFee(shippingFee)
                .total(total)
                .items(items)
                .totalWeight(totalWeight)  // set totalWeight ở đây
                .build();
    }



    private BigDecimal nvl(BigDecimal x) {
        return x == null ? BigDecimal.ZERO : x;
    }
}
