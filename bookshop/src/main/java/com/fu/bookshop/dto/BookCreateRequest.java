package com.fu.bookshop.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank(message = "Tên sách không được để trống")
    private String title;

    @NotBlank(message = "Tác giả không được để trống")
    private String authors;

    @NotBlank(message = "ISBN không được để trống")
    private String isbn;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải > 0")
    private BigDecimal price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được âm")
    @Max(value = 10000, message = "Số lượng không được quá 10000")
    private Integer quantity;

    // gram
    @NotNull(message = "Khối lượng không được để trống")
    @Min(value = 0, message = "Khối lượng không được âm")
    @Max(value = 10000, message = "Khối lượng không quá 10kg (10000g)")
    private Integer weight;

    @Size(max = 2000, message = "Mô tả không quá 2000 ký tự")
    private String description;

    @NotNull(message = "Ngày xuất bản không được để trống")
    @Past(message = "Ngày xuất bản phải là ngày trong quá khứ")
    private LocalDate publicationDate;

    @NotEmpty(message = "Phải chọn ít nhất 1 thể loại")
    private List<Long> categoryIds;

    @NotNull(message = "Phải chọn nhà xuất bản")
    private Long publisherId;


}
