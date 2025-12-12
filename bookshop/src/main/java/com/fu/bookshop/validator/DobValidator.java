package com.fu.bookshop.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DobValidator implements ConstraintValidator<ValidDob, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // ĐỂ @NotNull LO
        if (value == null) {
            return true;
        }

        if(value.isAfter(LocalDate.now())) {
            //vô hiệu hoá thông báo lỗi mặc định
            context.disableDefaultConstraintViolation();

            //Tuỳ chỉnh thông báo lỗi
            context.buildConstraintViolationWithTemplate("Ngày tháng năm sinh không thể là ngày trong tương lai")
                    .addConstraintViolation();

            return false;
        }

        long age = ChronoUnit.YEARS.between(value, LocalDate.now());
        if(age < 18) {
            //vô hiệu hoá thông báo lỗi mặc định
            context.disableDefaultConstraintViolation();

            //Tuỳ chỉnh thông báo lỗi
            context.buildConstraintViolationWithTemplate("Bạn cần đủ 18 tuổi để có thể tạo tài khoản mua hàng")
                    .addConstraintViolation();

            return false;
        }

        if(age > 130)  return false;


        return true;
    }
}
