package com.fu.bookshop.service.home.impl;

import com.fu.bookshop.entity.Shop;
import com.fu.bookshop.repository.ShopRepository;
import com.fu.bookshop.service.home.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;


    @Override
    public Shop getDefaultShop() {
        return shopRepository.findAll()
                .stream().findFirst().orElseThrow(() -> new RuntimeException("Khong co shop nao trong database"));
    }

    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Khong tim thay shop nao vs id " + id));
    }

    @Override
    public List<Shop> getALlShops() {
        return shopRepository.findAll();
    }
}
