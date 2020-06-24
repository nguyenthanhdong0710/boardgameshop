package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.OrderDetail;
import java.util.List;


public interface OrderDetailRepo extends Repo<OrderDetail> {
    List<OrderDetail> findByGameId(int id);
    List<OrderDetail> findByOrderId(int id);
    OrderDetail findByBothId(int orderId ,int gameId);
    void remove(int orderId ,int gameId);
    void removeAllByOrder(int orderId);
}
