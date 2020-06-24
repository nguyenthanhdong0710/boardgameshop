package com.vn.bgshop.boardgameshop.service;


import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import java.util.Optional;

public interface OrderStatusService {

     Optional<OrderStatus> findById(int id);

     Optional<OrderStatus> findByStatus(String status);


}
