package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.dto.OrderItemDTO;
import com.fu.bookshop.entity.CartItem;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.CartItemRepository;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

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

    private OrderDTO toDTO(Order order) {
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
