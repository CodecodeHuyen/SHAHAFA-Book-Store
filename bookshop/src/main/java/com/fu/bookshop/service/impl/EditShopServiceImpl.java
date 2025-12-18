package com.fu.bookshop.service.impl;

import com.fu.bookshop.entity.Shop;
import com.fu.bookshop.repository.ShopRepository;
import com.fu.bookshop.service.EditShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditShopServiceImpl implements EditShopService {

    private final ShopRepository shopRepository;

    @Override
    public Optional<Shop> getShop(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop updateShop(Long id, Shop updatedShop) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(updatedShop.getName());
            shop.setAddress(updatedShop.getAddress());
            shop.setLogoUrl(updatedShop.getLogoUrl());
            shop.setDescription(updatedShop.getDescription());
            shop.setEmail(updatedShop.getEmail());
            shop.setHotline(updatedShop.getHotline());
            shop.setBanners(updatedShop.getBanners());
            return shopRepository.save(shop);
        }).orElseThrow(() -> new RuntimeException("Shop not found"));
    }
}
