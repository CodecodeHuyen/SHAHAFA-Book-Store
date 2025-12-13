package com.fu.bookshop.dto;


import lombok.Data;

@Data
public class CartItemDTO {
    private Long subjectId;        // hoặc null
    private Long studyPackageId;   // hoặc null
    private String name;           // tên hiển thị ở PayOS
    private int price;             // VND
}