package com.fu.bookshop.repository;

import com.fu.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount_Email(String email);
}
