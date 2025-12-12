package com.fu.bookshop.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpValidationRequest {

    private String email;

    @NotBlank(message = "Otp không hợp lệ")
    private String otp;
}
