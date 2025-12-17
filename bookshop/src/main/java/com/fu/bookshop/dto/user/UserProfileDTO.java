package com.fu.bookshop.dto.user;

import com.fu.bookshop.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserProfileDTO {

    private Long id;
    private String name;
    private String avatarUrl;
    private Integer loyalPoint;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;


}
