package com.fu.bookshop.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    OTP_NOT_FOUND(1001, "Không tìm thấy mã OTP"),
    OTP_INVALID(1002, "Mã OTP không hợp lệ"),
    OTP_EXPIRED(1003, "Mã OTP đã hết hạn"),
    EMAIL_EXISTED(1004, "Email đã tồn tại"),
    ACCOUNT_DEACTIVATED(1005, "Tài khoản bị vô hiệu hóa bởi quản trị viên"),
    ROLE_NOT_FOUND(1006, "Role not found"),
    INVALID_FILE_URL(1007, "File URL is invalid"),
    FILE_DELETE_FAILED(1008, "File Delete Failed"),
    ACCOUNT_NOT_FOUND(1009, "Tài khoản không tồn tại trong hệ thống"),
    FILE_EMPTY(1010, "File không có ảnh nào"),
    FILE_LIMIT_EXCEEDED(1011, "Tệp file vượt quá 1000 ảnh"),
    ISBN_DUPLICATE(1012, "Sách đã tồn tại trong kho"),
    PUBLISHER_NOT_FOUND(1013, "Publisher not found"),
    FILE_UPLOAD_FAILED(1014, "Tải ảnh thất bại"),
    PUBLISHER_ALREADY_EXISTS(1015, "Nhà xuất bản này đã tồn tại"),
    BOOK_NOT_FOUND(1016, "Book not found"),
    CART_EMPTY(1017, "Giỏ hàng trống"),
    USER_NOT_FOUND(1018, "User not found"),
    INVALID_FILE_TYPE(1019, "File avatar không hợp lệ"),
    FILE_TOO_LARGE(1020, "File quá lớn"),
    ORDER_NOT_FOUND(1021, "Order not found"),
    ADMIN_ACCOUNT_CANNOT_DELETE(1030, "Không thể xoá Role Admin");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
