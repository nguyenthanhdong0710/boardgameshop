package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.CartDetail;
import com.vn.bgshop.boardgameshop.repository.CartDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepo cartDetailRepo;


    @Override
    public List<CartDetail> findByGameId(int id) {
        return cartDetailRepo.findByGameId(id);
    }

    @Override
    public List<CartDetail> findByUserId(int id) {
        return cartDetailRepo.findByUserId(id);
    }

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepo.findAll();
    }

    @Override
    public CartDetail findById(int id) {
        return null;
    }

    @Override
    public CartDetail findByBothId(int userId, int gameId) {
        return cartDetailRepo.findByBothId(userId,gameId);
    }

    @Override
    public void save(CartDetail model) {
        cartDetailRepo.save(model);
    }

    @Override
    public void remove(int userId, int gameId) {
        cartDetailRepo.remove(userId,gameId);
    }

    @Override
    public void removeAllByUser(int userId) {
        cartDetailRepo.removeAllByUser(userId);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, CartDetail model) {

    }


}
