package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.CheckoutDTO;
import com.fu.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.fu.bookshop.service.UserService {

    private final UserRepository userRepository;

//    @Transactional
//    @Override
//    public CheckoutDTO getCheckoutImfor(String email) {
//        return userRepository.findByEmail(email)
//                .map(user -> CheckoutDTO.builder()
//                        .email(user.getAccount().getEmail())
//                        .username(user.getName())
//                        .loyalPoint(user.getLoyalPoint())
//                        .build()
//                )
//                .orElse(null);
//
//
//    }

}
