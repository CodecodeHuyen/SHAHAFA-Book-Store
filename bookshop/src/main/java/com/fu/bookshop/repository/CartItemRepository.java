package com.fu.bookshop.repository;

import com.fu.bookshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.book WHERE ci.cart.id = :cartId")
    List<CartItem> findByCartIdWithBook(@Param("cartId") Long cartId);
    
    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.book WHERE ci.id = :id")
    java.util.Optional<CartItem> findByIdWithBook(@Param("id") Long id);
    
    void deleteByCartId(Long cartId);
}

