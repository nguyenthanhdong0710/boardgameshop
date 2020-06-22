package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Order;
import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import com.vn.bgshop.boardgameshop.entity.User;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    List<Order> findByUser(User user);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByOrderStatusAndUser(OrderStatus orderStatus, User user);

    List<Order> findAll();

    Optional<Order> findById(int id);

    void save(Order model);

    Order saveAndFlush(Order model);

    void remove(int id);

    void update(int id, Order model);
}
