package com.fu.bookshop.dto;

import com.fu.bookshop.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private String authors;
    private String isbn;

    private String publisherName;
    private String categoryNames; // "Văn học, Kinh tế"

    private Integer quantity;
    private BookStatus status;

    private String urlImage;
}
