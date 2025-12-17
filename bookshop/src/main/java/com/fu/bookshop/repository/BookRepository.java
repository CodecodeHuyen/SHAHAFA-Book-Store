package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Book;
import com.fu.bookshop.enums.BookStatus;
import com.fu.bookshop.enums.OrderStatus;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<Book> findByStatus (BookStatus status, Pageable pageable);
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

    @Query("""
        select b from Book b 
        join fetch b.publisher
        join fetch b.categories
        where b.id = :id
""")
    Optional<Book> findDetailById(@Param("id")Long id);

    @Query("""
    select distinct b from Book b 
    join b.categories c 
    where c.id in :categoryIds
    and b.id <> :bookId
    and b.status = :status
""")
    List<Book> findRelatedByCategories(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("bookId") Long bookId,
            @Param("status") BookStatus status,
            Pageable pageable
    );

    List<Book> findByPublisherIdAndIdNotAndStatus(
            Long publisherId,
            Long bookId,
            BookStatus status,
            Pageable pageable
    );

    @Query("""
    select b 
    from OrderItem oi 
    join oi.book b 
    join oi.order o 
    where o.orderStatus = :orderStatus
    group by b
    order by sum(oi.quantity) desc 
""")
    List<Book> findBestSellerBooks(
            @Param("orderStatus")OrderStatus orderStatus,
            Pageable pageable
            );

    @Query("""
        SELECT DISTINCT b
        FROM Book b
        LEFT JOIN b.publisher p
        LEFT JOIN b.categories c
        WHERE b.status <> com.fu.bookshop.enums.BookStatus.DELETED
          AND (:bookStatus IS NULL OR b.status = :bookStatus)
          AND (:publisherId IS NULL OR p.id = :publisherId)
          AND (:categoryIds IS NULL OR c.id IN :categoryIds)
          AND (
                :keyword IS NULL OR
                LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
          )
    """)
    Page<Book> findFilteredBooks(
            @Param("bookStatus") BookStatus bookStatus,
            @Param("publisherId") Long publisherId,
            @Param("categoryIds") List<Long> categoryIds,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    Boolean existsByIsbn(String isbn);

}
