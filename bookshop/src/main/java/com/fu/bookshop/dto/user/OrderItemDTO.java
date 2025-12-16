package com.fu.bookshop.dto.user;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDTO {
    private Long bookId;
    private String bookTitle;
    private String bookImage;

    private Integer quantity;

    private BigDecimal unitPrice;

    public BigDecimal getSubTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
