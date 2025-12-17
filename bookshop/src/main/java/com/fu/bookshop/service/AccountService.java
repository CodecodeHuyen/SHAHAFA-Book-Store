package com.fu.bookshop.service;

import com.fu.bookshop.dto.AccountListItemDTO;
import com.fu.bookshop.dto.AccountCreateRequest;
import com.fu.bookshop.enums.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Page<AccountListItemDTO> getAccounts(AccountStatus status,
                                         String keyword,
                                         Pageable pageable);

    AccountListItemDTO createAccount(AccountCreateRequest request);

    void deleteAccount(Long accountId);

    void toggleAccountStatus(Long accountId);
}

