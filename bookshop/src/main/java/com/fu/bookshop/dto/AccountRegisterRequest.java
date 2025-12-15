package com.fu.bookshop.dto;

import com.fu.bookshop.enums.Gender;
import com.fu.bookshop.validator.ValidDob;
import com.fu.bookshop.validator.ValidPassword;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRegisterRequest {

    @NotBlank
    @Email
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
    @ValidPassword
    private String confirmPassword;
}
