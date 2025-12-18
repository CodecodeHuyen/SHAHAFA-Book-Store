package com.fu.bookshop.dto.user;

import com.fu.bookshop.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class UserProfileDTO {

    private Long id;
    @NotBlank(message = "Full name must not be blank")
    @Size(min = 2, max = 255, message = "Full name must be between 2 and 255 characters")
    private String name;

    private String avatarUrl;

    @PositiveOrZero(message = "Loyalty point must be zero or positive")
    private Integer loyalPoint;
    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender must be selected")
    private Gender gender;


    private String email;


}
