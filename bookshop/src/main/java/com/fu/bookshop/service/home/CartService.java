package com.fu.bookshop.service.home;

import com.fu.bookshop.dto.CartDTO;
import com.fu.bookshop.entity.Account;

public interface CartService {
    CartDTO getCartByAccount(Account account);
    void addToCart(Account account, Long bookId, Integer quantity);
    void updateCartItemQuantity(Account account, Long cartItemId, Integer quantity);
    void removeCartItem(Account account, Long cartItemId);
}

