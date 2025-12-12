package com.fu.bookshop.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "payment_method")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "NVARCHAR(50)")
    private String methodName;

    @Column(nullable = false)
    private Boolean isActive;
    @Column(nullable = false)
    private String paymentCode;
    @Column(nullable = false)
    private String imageUrl;
    @Column(columnDefinition = "NVARCHAR(50)")
    private String bankName;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;
}
