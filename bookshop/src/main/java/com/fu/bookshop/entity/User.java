package com.fu.bookshop.entity;
import com.fu.bookshop.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(nullable = false)
    private Integer loyalPoint;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false,columnDefinition = "NVARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String avatarUrl;



    @OneToOne
    @JoinColumn(name = "account_id") //tên cột khoas ngoại
    private Account account;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
