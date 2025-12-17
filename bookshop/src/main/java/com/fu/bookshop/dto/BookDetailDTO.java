package com.fu.bookshop.dto;

import com.fu.bookshop.enums.BookStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailDTO {


    private String title;
    private String image;
    private BigDecimal price;
    private String description;
    private String authors;
    private String publisherName;
    private LocalDate publicationDate;
    private Integer weight;
    private String isbn;
    private String categoryNames;

    private Long id;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private Integer stock;

}
