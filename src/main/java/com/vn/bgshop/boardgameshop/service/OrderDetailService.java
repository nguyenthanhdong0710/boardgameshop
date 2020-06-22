package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

     List<OrderDetail> findByGameId(int id);
     List<OrderDetail> findByOrderId(int id);
     OrderDetail findByBothId(int orderId ,int gameId);
     void remove(int orderId ,int gameId);
     void removeAllByOrder(int orderId);
     List<OrderDetail> findAll();
     void save(OrderDetail model);
     
     void remove(int id);
     
     void update(int id, OrderDetail model);
}
