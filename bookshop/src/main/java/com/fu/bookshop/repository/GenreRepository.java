package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Category, Long> {
}
