package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import com.vn.bgshop.boardgameshop.repository.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

     @Autowired
     private OrderStatusRepo orderStatusRepo;

     @Override
     public Optional<OrderStatus> findById(int id) {
          return orderStatusRepo.findById(id);
     }

     @Override
     public Optional<OrderStatus> findByStatus(String status) {
          return orderStatusRepo.findByStatus(status);
     }

}
