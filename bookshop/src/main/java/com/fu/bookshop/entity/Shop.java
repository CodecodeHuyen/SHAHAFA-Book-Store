package com.fu.bookshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(nullable = false,columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(nullable = false)
    private String logoUrl;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "shop")
    private List<Book> books;
}
