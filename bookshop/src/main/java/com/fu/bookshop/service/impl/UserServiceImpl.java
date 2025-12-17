package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.OrderItemDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.enums.PaymentStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.exception.SystemException;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private S3ServiceImpl s3Service;

    @Override
    public UserProfileDTO getUserProfileByEmail(String email) {
        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));

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
        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return mapOrders(
                orderRepository.findByUser_IdOrderByIdDesc(user.getId())
        );
    }

    @Override
    public List<OrderHistoryDTO> getOrderHistoryByStatus(String email, OrderStatus status) {
        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return mapOrders(
                orderRepository.findByUser_IdAndOrderStatus(
                        user.getId(), status
                )
        );
    }

    @Override
    public UserProfileDTO getByEmail(String email) {
        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));

        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getAccount().getEmail())
                .loyalPoint(user.getLoyalPoint())
                .build();
    }

    @Override
    public void updateProfile(String email, String name, LocalDate dateOfBirth, Gender gender, MultipartFile avatar) {
        User user = userRepository.findByAccount_Email(email)
                .orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));

        user.setName(name);
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);

        if(avatar != null && !avatar.isEmpty()){

            //xoa avatar cu neu co
            if(user.getAvatarUrl()!= null){
                s3Service.delete(user.getAvatarUrl());
            }

            String avatarUrl = s3Service.uploadAvatar(avatar);
            user.setAvatarUrl(avatarUrl);
        }

        userRepository.save(user);
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
