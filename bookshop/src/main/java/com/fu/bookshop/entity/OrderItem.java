package com.fu.bookshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;   // giá book lúc mua

//    @Column(nullable = false)
//    private Integer weight;
//
//    // --- Lifecycle Callbacks ---
//
//    @PrePersist
//    @PreUpdate
//    private void calculateWeight() {
//        if (this.book != null && this.quantity != null) {
//            // Lấy weight của book nhân với số lượng
//            // Giả định weight trong Book không được null, nếu có thể null hãy thêm check
//            Integer bookWeight = this.book.getWeight() != null ? this.book.getWeight() : 0;
//            this.weight = bookWeight * this.quantity;
//        } else {
//            this.weight = 0;
//        }
//    }
}
