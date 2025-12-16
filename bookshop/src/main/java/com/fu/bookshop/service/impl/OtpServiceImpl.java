package com.fu.bookshop.service.impl;

import com.fu.bookshop.dto.OtpValidationRequest;
import com.fu.bookshop.entity.OtpCode;
import com.fu.bookshop.exception.BusinessException;
import com.fu.bookshop.exception.ErrorCode;
import com.fu.bookshop.repository.OtpCodeRepository;
import com.fu.bookshop.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "OTP-SERVICE")
public class OtpServiceImpl implements OtpService {

    // =====  POLICY =====
    private static final Duration EXPIRES_IN = Duration.ofMinutes(4);
    private static final SecureRandom RNG = new SecureRandom();
    private final OtpCodeRepository otpRepo;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;


    private static String generateOtp(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(RNG.nextInt(10));
        return sb.toString();
    }

    @Transactional
    @Override
    public void sendOtp(String email, String name) {
        var now = Instant.now();
        log.info("Time " + now);
        OtpCode code = otpRepo.findByEmail(email).orElse(null);

        String otp = generateOtp(6);
        String hash = passwordEncoder.encode(otp);

        if (code == null) {
            code = OtpCode.builder()
                    .email(email)
                    .otpHash(hash)
                    .expiresAt(now.plus(EXPIRES_IN))
                    .used(false)
                    .build();
        } else {
            code.setOtpHash(hash);
            code.setExpiresAt(now.plus(EXPIRES_IN));
            code.setUsed(false);
        }

        otpRepo.save(code);
        emailService.sendEmail(email, "[SHAHAFA Book Store] Mã Xác Minh Của Bạn", "Chào " + name + ". Mã OTP của bạn là: " + otp);
    }


    @Transactional
    @Override
    public boolean verifyOtp(OtpValidationRequest request) {
        OtpCode code = otpRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.OTP_NOT_FOUND));

        if (code.isUsed() || code.getExpiresAt().isBefore(Instant.now())) {
            throw new BusinessException(ErrorCode.OTP_EXPIRED);
        }

        if (!passwordEncoder.matches(request.getOtp(), code.getOtpHash())) {
            throw new BusinessException(ErrorCode.OTP_INVALID);
        }

        code.setUsed(true);
        otpRepo.save(code);
        return true;
    }

}
