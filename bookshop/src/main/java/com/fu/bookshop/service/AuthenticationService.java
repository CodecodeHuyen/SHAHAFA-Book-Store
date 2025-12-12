package com.fu.bookshop.service;

import com.fu.bookshop.dto.AccountRegisterRequest;
import com.fu.bookshop.dto.OtpValidationRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    long signUp(AccountRegisterRequest req);

    @Transactional
    void verifyAccountAndUpdateStatus(OtpValidationRequest request);
}
