package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.OrderItemDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.enums.PaymentStatus;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public UserProfileDTO getUserProfileByEmail(String email) {
        User user = userRepository.findByAccount_Email(email);

        if(user==null){
            System.out.println("Null nè");
        }
        return  UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .avatarUrl(user.getAvatarUrl())
                .loyalPoint(user.getLoyalPoint())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .build();

    }

    @Override
    public List<OrderHistoryDTO> getOrderHistory(String email) {
        User user = userRepository.findByAccount_Email(email);

        return mapOrders(
                orderRepository.findByUser_IdOrderByIdDesc(user.getId())
        );
    }

    @Override
    public List<OrderHistoryDTO> getOrderHistoryByStatus(String email, OrderStatus status) {
        User user = userRepository.findByAccount_Email(email);

        return mapOrders(
                orderRepository.findByUser_IdAndOrderStatus(
                        user.getId(), status
                )
        );
    }

    private List<OrderHistoryDTO> mapOrders(List<Order> orders){
        List<OrderHistoryDTO> result = new ArrayList<>();

        for(Order order : orders){
            List<OrderItemDTO> itemDTOS = new ArrayList<>();

            for(OrderItem item : order.getOrderItems()){
                OrderItemDTO itemDTO = OrderItemDTO.builder()
                        .bookId(item.getBook().getId())
                        .bookTitle(item.getBook().getTitle())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build();

                itemDTOS.add(itemDTO);
            }

            PaymentStatus paymentStatus = null;
            String paymentMethodName = null;
            String paymentMethodImage = null;

            if(order.getPayment() != null){
                paymentStatus = order.getPayment().getPaymentStatus();

                if(order.getPayment().getPaymentMethod() != null){
                    paymentMethodName =
                            order.getPayment().getPaymentMethod().getMethodName();

                    paymentMethodImage = order.getPayment().getPaymentMethod().getImageUrl();

                }
            }

            OrderHistoryDTO orderDTO = OrderHistoryDTO.builder()
                    .orderId(order.getId())
                    .orderCode(order.getCode())
                    .orderStatus(order.getOrderStatus())
                    .totalPrice(order.getTotalPrice())
                    .discount(order.getDiscount())
                    .shippingFee(order.getShippingFee())
                    .pickupAddr(order.getPickupAddr())
                    .deliveryAddr(order.getDeliveryAddr())
                    .paymentStatus(paymentStatus)
                    .paymentMethodName(paymentMethodName)
                    .paymentMethodImage(paymentMethodImage)
                    .items(itemDTOS)
                    .build();

            result.add(orderDTO);
        }

        return result;

    }
}
