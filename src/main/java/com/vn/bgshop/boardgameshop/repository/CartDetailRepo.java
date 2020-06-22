package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.CartDetail;

import java.util.List;


public interface CartDetailRepo extends Repo<CartDetail> {
    List<CartDetail> findByGameId(int id);
    List<CartDetail> findByUserId(int id);
    CartDetail findByBothId(int userId ,int gameId);
    void remove(int userId ,int gameId);
    void removeAllByUser(int userId);
}
