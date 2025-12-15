package com.fu.bookshop.service.user;

import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.enums.OrderStatus;

import java.util.List;

public interface UserService {

    UserProfileDTO getUserProfileByEmail(String email);

    List<OrderHistoryDTO> getOrderHistory(String email);

    List<OrderHistoryDTO> getOrderHistoryByStatus(
            String email,
            OrderStatus status
    );

}
