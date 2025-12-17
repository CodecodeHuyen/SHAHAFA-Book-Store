package com.fu.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;   // true / false
    private int code;          // business code
    private String message;    // message cho UI
    private T data;            // payload (nullable)
}