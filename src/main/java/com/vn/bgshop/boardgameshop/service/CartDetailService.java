package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.CartDetail;

import java.util.List;

public interface CartDetailService {

     List<CartDetail> findByGameId(int id);

     List<CartDetail> findByUserId(int id);

     List<CartDetail> findAll();

     CartDetail findById(int id);

     CartDetail findByBothId(int userId, int gameId);

     void save(CartDetail model);

     void remove(int userId, int gameId);

     void removeAllByUser(int userId);

     void remove(int id);

     void update(int id, CartDetail model);
}
