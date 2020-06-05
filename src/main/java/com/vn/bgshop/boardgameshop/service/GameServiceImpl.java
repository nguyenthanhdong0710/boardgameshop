package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepo gameRepo;


    @Override
    public Game findByName(String name) {
        return null;
    }

    @Override
    public List<Game> findAll() {
        return gameRepo.findAll();
    }

    @Override
    public List<Game> findAllDeleted() {
        return gameRepo.findAllDeleted();
    }

    @Override
    public Game findById(int id) {
        return gameRepo.findById(id);
    }

    @Override
    public void save(Game model) {
        gameRepo.save(model);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Game model) {

    }
}
