package com.fu.bookshop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherCreateRequest {

    @NotBlank(message = "Tên publisher không được để trống")
    @Size(max = 50, message = "Tên publisher tối đa 50 ký tự")
    private String name;
}
