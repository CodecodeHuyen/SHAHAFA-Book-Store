package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(String email);

}


