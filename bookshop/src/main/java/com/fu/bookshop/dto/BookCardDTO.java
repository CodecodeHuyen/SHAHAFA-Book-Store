package com.fu.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCardDTO {
    private Long id;
    private String title;
    private String image;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Integer salePercent;
}
