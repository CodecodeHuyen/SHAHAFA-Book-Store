package com.fu.bookshop.dto;

import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.validator.ValidDob;
import com.fu.bookshop.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreateRequest {

    @NotBlank
    @Email(message = "Email phải đúng định dạng, ví dụ: abc@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @ValidDob
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String roleName; // only MANAGER or CUSTOMER
}

