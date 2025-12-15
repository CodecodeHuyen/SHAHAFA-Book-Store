package com.fu.bookshop.service.impl;

import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j(topic = "USERDETAILS-SERVICE-IMPL")
public class UserDetailsServiceImpl implements UserDetailsService {

     private final AccountRepository accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND));
    }
}
