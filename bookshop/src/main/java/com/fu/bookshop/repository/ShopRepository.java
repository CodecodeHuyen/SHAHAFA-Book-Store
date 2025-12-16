package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository {
    Optional<Shop> findFirstByOrderByIdAsc();
}
