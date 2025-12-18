package com.fu.bookshop.service;

import com.fu.bookshop.entity.Shop;

import java.util.Optional;

public interface EditShopService {
    Optional<Shop> getShop(Long id);
    Shop updateShop(Long id, Shop shop);
}
