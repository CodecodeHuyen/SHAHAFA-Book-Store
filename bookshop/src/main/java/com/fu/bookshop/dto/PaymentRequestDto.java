package com.fu.bookshop.dto;


import lombok.Data;

import java.util.List;

@Data
public class PaymentRequestDto {
    private String description;
    private int amount; // tổng tiền client gửi (server vẫn nên tự tính lại)
    private List<CartItemDTO> items;
}
