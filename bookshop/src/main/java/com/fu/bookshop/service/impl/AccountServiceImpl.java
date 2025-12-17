package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.AccountCreateRequest;
import com.fu.bookshop.dto.AccountListItemDTO;
import com.fu.bookshop.entity.Account;
import com.fu.bookshop.entity.Role;
import com.fu.bookshop.entity.User;
import com.fu.bookshop.enums.AccountStatus;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.AccountRepository;
import com.fu.bookshop.repository.RoleRepository;
import com.fu.bookshop.repository.UserRepository;
import com.fu.bookshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<AccountListItemDTO> getAccounts(AccountStatus status, String keyword, Pageable pageable) {
        Page<Account> accounts = accountRepository.searchAccounts(status, keyword, pageable);

        return accounts.map(account -> AccountListItemDTO.builder()
                .id(account.getId())
                .fullName(account.getUser() != null ? account.getUser().getName() : "")
                .email(account.getEmail())
                .role(account.getRoles() != null && !account.getRoles().isEmpty()
                        ? account.getRoles().get(0).getRoleName()
                        : "")
                .status(account.getAccountStatus())
                .build());
    }

    @Override
    @Transactional
    public AccountListItemDTO createAccount(AccountCreateRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_EXISTED);
        }

        Set<String> allowedRoles = Set.of("MANAGER", "CUSTOMER");
        if (!allowedRoles.contains(request.getRoleName())) {
            throw new BusinessException(ErrorCode.ROLE_NOT_FOUND);
        }

        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));

        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountStatus(AccountStatus.PENDING)
                .roles(List.of(role))
                .build();

        account = accountRepository.save(account);

        User user = User.builder()
                .name(request.getName())
                .loyalPoint(0)
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .account(account)
                .build();

        userRepository.save(user);

        return AccountListItemDTO.builder()
                .id(account.getId())
                .fullName(user.getName())
                .email(account.getEmail())
                .role(role.getRoleName())
                .status(account.getAccountStatus())
                .build();
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND));

        boolean isAdmin = account.getRoles() != null &&
                account.getRoles().stream()
                        .anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getRoleName()));

        if (isAdmin) {
            throw new BusinessException(ErrorCode.ADMIN_ACCOUNT_CANNOT_DELETE);
        }

        userRepository.findByAccount(account).ifPresent(userRepository::delete);
        accountRepository.delete(account);
    }

    @Override
    @Transactional
    public void toggleAccountStatus(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND));

        AccountStatus currentStatus = account.getAccountStatus();

        if (currentStatus == AccountStatus.ACTIVE) {
            account.setAccountStatus(AccountStatus.DEACTIVATED);
        } else if (currentStatus == AccountStatus.DEACTIVATED) {
            account.setAccountStatus(AccountStatus.ACTIVE);
        } else {
            // PENDING: tạm thời không thay đổi
            return;
        }

        accountRepository.save(account);
    }
}

