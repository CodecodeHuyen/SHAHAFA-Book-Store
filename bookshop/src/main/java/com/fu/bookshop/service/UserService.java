package com.fu.bookshop.service;


import com.fu.bookshop.dto.CheckoutDTO;
import com.fu.bookshop.dto.user.OrderHistoryDTO;
import com.fu.bookshop.dto.user.UserProfileDTO;
import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.OrderStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    UserProfileDTO getUserProfileByEmail(String email);

    List<OrderHistoryDTO> getOrderHistory(String email);

    List<OrderHistoryDTO> getOrderHistoryByStatus(String email, OrderStatus status);

    void updateProfile(String email, String name, LocalDate dateOfBirth, Gender gender, MultipartFile avatar);


    UserProfileDTO getByEmail(String email);
}