package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Order;
import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;


    @Override
    public List<Order> findByUser(User user) {
        return orderRepo.findByUser(user);
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.findByOrderStatus(orderStatus);
    }

    @Override
    public List<Order> findByOrderStatusAndUser(OrderStatus orderStatus, User user) {
        return orderRepo.findByOrderStatusAndUser(orderStatus,user);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepo.findById(id);
    }

    @Override
    public void save(Order model) {
        orderRepo.save(model);
    }

    @Override
    public Order saveAndFlush(Order model) {
        return orderRepo.saveAndFlush(model);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Order model) {

    }
}
