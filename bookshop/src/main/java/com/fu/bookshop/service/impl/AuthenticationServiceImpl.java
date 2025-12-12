package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.AccountRegisterRequest;
import com.fu.bookshop.dto.OtpValidationRequest;
import com.fu.bookshop.entity.Account;
import com.fu.bookshop.entity.Role;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.AccountStatus;
import com.fu.bookshop.exception.AppException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.AccountRepository;
import com.fu.bookshop.repository.RoleRepository;
import com.fu.bookshop.service.AuthenticationService;
import com.fu.bookshop.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-SERVICE-IMPL")
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AccountRepository accountRepository;
//    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final OtpService otpService;


    @Override
    public long signUp(AccountRegisterRequest req) {

        Optional<Account> optionalAccount = accountRepository.findAccountByEmail(req.getEmail());

        if (optionalAccount.isPresent()) {

            Account account = optionalAccount.get();

            if (account.getAccountStatus().equals(AccountStatus.ACTIVE)) {
                throw new AppException(ErrorCode.EMAIL_EXISTED);
            } else if (account.getAccountStatus().equals(AccountStatus.DEACTIVATED)) {
                throw new AppException(ErrorCode.ACCOUNT_DEACTIVATED);
            }

            User user = account.getUser();
            if (user == null) {
                user = new User();
                user.setAccount(account);
            }
            user.setName(req.getName());
            user.setDateOfBirth(req.getDateOfBirth());
            user.setGender(req.getGender());
            user.setLoyalPoint(0);

            account.setUser(user);
//            account.setPassword(encoder.encode(req.getPassword()));
            account.setPassword(req.getPassword());


            account.setAccountStatus(AccountStatus.PENDING);

            accountRepository.save(account);

            otpService.sendOtp(req.getEmail(), req.getName());
            return account.getId();
        }

        Role customer = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new IllegalStateException("Role CUSTOMER not found"));

        User user = User.builder()
                .name(req.getName())
                .dateOfBirth(req.getDateOfBirth())
                .gender(req.getGender())
                 .loyalPoint(0)
                .build();

        Account account = Account.builder()
                .email(req.getEmail())
//                .password(encoder.encode(req.getPassword()))
                .password(req.getPassword())
                .accountStatus(AccountStatus.PENDING)
                .roles(List.of(customer))
                .user(user)
                .build();


        user.setAccount(account);
        accountRepository.save(account);


        otpService.sendOtp(req.getEmail(), req.getName());
        return account.getId();
    }

    @Transactional
    @Override
    public void verifyAccountAndUpdateStatus(OtpValidationRequest request) {
        boolean verified = otpService.verifyOtp(request);

        if (verified) {
            Account account = accountRepository.findAccountByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalStateException("Account not found"));

            account.setAccountStatus(AccountStatus.ACTIVE);
            accountRepository.save(account);
        }
    }

}
