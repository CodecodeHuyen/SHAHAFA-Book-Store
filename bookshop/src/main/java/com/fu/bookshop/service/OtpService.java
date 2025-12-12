package com.fu.bookshop.service;

import com.fu.bookshop.dto.OtpValidationRequest;

public interface OtpService {
    void sendOtp(String email, String name);

    boolean verifyOtp(OtpValidationRequest otpValidationRequest);
}