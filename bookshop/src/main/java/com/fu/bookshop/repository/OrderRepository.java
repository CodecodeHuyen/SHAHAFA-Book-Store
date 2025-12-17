package com.fu.bookshop.repository;

import com.fu.bookshop.entity.Order;
import com.fu.bookshop.enums.OrderStatus;
import org.hibernate.metamodel.mapping.ordering.ast.OrderingExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_IdOrderByIdDesc(Long userId);

    List<Order> findByUser_IdAndOrderStatus(
            Long userId,
            OrderStatus orderStatus
    );
    Order findByCode(String code);
}
