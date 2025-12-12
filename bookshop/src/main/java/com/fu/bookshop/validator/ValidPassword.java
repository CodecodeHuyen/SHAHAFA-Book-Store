package com.fu.bookshop.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    int min() default 8;
    int max() default 64;

    String message() default "Mật khẩu phải từ {min} đến {max} ký tự";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}