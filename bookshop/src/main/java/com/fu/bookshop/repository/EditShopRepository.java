package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditShopRepository extends JpaRepository<Shop, Long> {
}