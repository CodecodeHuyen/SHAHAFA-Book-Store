package com.fu.bookshop.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // OTP_NOT_FOUND(1001, "OTP not found"),
    OTP_NOT_FOUND(1001, "Không tìm thấy mã OTP"),

    // OTP_INVALID(1002, "Invalid OTP"),
    OTP_INVALID(1002, "Mã OTP không hợp lệ"),

    // OTP_EXPIRED(1003, "OTP expired"),
    OTP_EXPIRED(1003, "Mã OTP đã hết hạn"),

    // EMAIL_EXISTED(1004, "Email existed"),
    EMAIL_EXISTED(1004, "Email đã tồn tại"),

    //
    ACCOUNT_DEACTIVATED(1005, "Tài khoản bị vô hiệu hóa bởi quản trị viên");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
