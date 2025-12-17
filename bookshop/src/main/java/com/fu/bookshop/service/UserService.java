package com.fu.bookshop.service;


import com.fu.bookshop.dto.CheckoutDTO;
import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    UserProfileDTO getUserProfileByEmail(String email);

    List<OrderHistoryDTO> getOrderHistory(String email);

    List<OrderHistoryDTO> getOrderHistoryByStatus(String email, OrderStatus status);

    UserProfileDTO getByEmail(String email);




}