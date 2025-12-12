package com.fu.bookshop.entity;
import com.fu.bookshop.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;
}
