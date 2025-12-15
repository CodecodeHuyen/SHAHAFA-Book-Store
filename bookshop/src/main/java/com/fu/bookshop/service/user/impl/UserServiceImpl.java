package com.fu.bookshop.service.user.impl;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.OrderItemDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.OrderItem;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import com.fu.bookshop.repository.OrderRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.UserDatabaseRealm;
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

            }
        }
    }
}
