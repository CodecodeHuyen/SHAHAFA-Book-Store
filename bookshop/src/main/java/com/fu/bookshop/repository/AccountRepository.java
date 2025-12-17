package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Account;
import com.fu.bookshop.enums.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
            SELECT a
            FROM Account a
            LEFT JOIN a.user u
            WHERE (:status IS NULL OR a.accountStatus = :status)
              AND (
                    :keyword IS NULL
                 OR :keyword = ''
                 OR LOWER(a.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(u.name)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
            """)
    Page<Account> searchAccounts(@Param("status") AccountStatus status,
                                 @Param("keyword") String keyword,
                                 Pageable pageable);

}


