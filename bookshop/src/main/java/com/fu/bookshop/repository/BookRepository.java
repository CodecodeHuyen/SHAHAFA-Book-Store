package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Book;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<Book> findByStatus (String status, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Book> findByCategories_Id(Long categoryId, Pageable pageable);

    Page<Book> findByPublisher_Id(Long publisherId, Pageable pageable);

    Page<Book> findDistinctByCategories_IdIn(List<Long> categoryIds, Pageable pageable);

    Page<Book> findDistinctByCategories_IdInAndPublisher_Id(
            List<Long> categoryIds,
            Long publisherId,
            Pageable pageable
    );
}
