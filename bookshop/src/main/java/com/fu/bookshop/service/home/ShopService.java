package com.fu.bookshop.service.home;

import com.fu.bookshop.entity.Shop;

import java.util.List;

public interface ShopService {

    Shop getDefaultShop();

    Shop getShopById(Long id);

    List<Shop> getALlShops();
}
