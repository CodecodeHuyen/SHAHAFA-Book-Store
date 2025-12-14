package com.fu.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailDTO {

    private Long id;
    private String title;
    private String image;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Integer salePercent;

    private String description;
    private String authors;

    private String publisherName;
    private LocalDate publicationDate;
    private String language;
    private Integer pages;
    private Integer weight;
    private String size;

}
