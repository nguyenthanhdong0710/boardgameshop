package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderStatusRepo extends JpaRepository<OrderStatus,Integer> {

    Optional<OrderStatus> findByStatus(String status);
}
