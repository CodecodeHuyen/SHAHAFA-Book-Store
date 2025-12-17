package com.fu.bookshop.repository;
import com.fu.bookshop.entity.Account;
import com.fu.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(Account account);
    User findByAccount_Email(String email);
}

