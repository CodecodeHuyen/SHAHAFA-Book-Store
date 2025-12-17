package com.fu.bookshop.dto;

import com.fu.bookshop.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountListItemDTO {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private AccountStatus status;
}

