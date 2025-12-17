package com.fu.bookshop.service;

import com.fu.bookshop.dto.OrderDTO;
import com.fu.bookshop.entity.Order;
import com.fu.bookshop.entity.User;

import java.util.List;

public interface OrderService {
    OrderDTO createCheckout(String email, List<Long> cartItemIds);
}
