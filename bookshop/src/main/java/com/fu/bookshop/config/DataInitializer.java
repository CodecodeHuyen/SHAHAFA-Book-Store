package com.fu.bookshop.config;

import com.fu.bookshop.entity.Account;
import com.fu.bookshop.entity.Role;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.AccountStatus;
import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.repository.AccountRepository;
import com.fu.bookshop.repository.RoleRepository;
import com.fu.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${admin.email_default}")
    @NonFinal
    String EMAIL_ADMIN;

    @Value("${admin.password_default}")
    @NonFinal
    String PASSWORD_ADMIN;

    @Value("${customer-account-test.email}")
    @NonFinal
    String EMAIL_CUSTOMER;

    @Value("${customer-account-test.password}")
    @NonFinal
    String PASSWORD_CUSTOMER;

    @Value("${manager-account-test.email}")
    @NonFinal
    String EMAIL_MANAGER;

    @Value("${manager-account-test.password}")
    @NonFinal
    String PASSWORD_MANAGER;



    @Override
    public void run(String... args) {

        /* =========================
           1. INIT ROLE
           ========================= */
        Role customer = createRoleIfNotExist("CUSTOMER", "Khách hàng sử dụng hệ thống");
        Role manager  = createRoleIfNotExist("MANAGER", "Quản lý hệ thống");
        Role admin    = createRoleIfNotExist("ADMIN", "Quản trị viên hệ thống");

        /* =========================
           2. INIT ACCOUNT + USER
           ========================= */
        createAccountWithUser(
               EMAIL_ADMIN,
                PASSWORD_ADMIN,
                admin,
                "Admin System"
        );

        createAccountWithUser(
               EMAIL_CUSTOMER,
               PASSWORD_CUSTOMER,
                customer,
                "Customer Test"
        );

        createAccountWithUser(
               EMAIL_MANAGER,
                PASSWORD_MANAGER,
                manager,
                "Manager Test"
        );
    }

    /* =========================
       SUPPORT METHODS
       ========================= */

    private Role createRoleIfNotExist(String roleName, String description) {
        return roleRepository.findByRoleName(roleName)
                .orElseGet(() -> {
                    Role role = Role.builder()
                            .roleName(roleName)
                            .description(description)
                            .build();
                    return roleRepository.save(role);
                });
    }

    private void createAccountWithUser(
            String email,
            String rawPassword,
            Role role,
            String fullName
    ) {

        if (accountRepository.existsByEmail(email)) {
            log.info("Account {} already exists, skip init", email);
            return;
        }

        Account account = Account.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .accountStatus(AccountStatus.ACTIVE)
                .roles(List.of(role))
                .build();

        accountRepository.save(account);

        User user = User.builder()
                .name(fullName)
                .account(account)
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .gender(Gender.FEMALE)
                .loyalPoint(0)
                .build();

        userRepository.save(user);

        log.info("Initialized account {}", email);
    }
}
