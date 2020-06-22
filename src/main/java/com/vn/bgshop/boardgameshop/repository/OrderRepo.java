package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Order;
import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByOrderStatusAndUser(OrderStatus orderStatus, User user);
}
