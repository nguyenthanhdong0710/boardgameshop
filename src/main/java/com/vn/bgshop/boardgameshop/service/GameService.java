package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Game;


import java.util.List;

public interface GameService {

    Game findByName(String name);

    List<Game> findAll();

    Game findById(int id);

    void save(Game model);

    void remove(int id);

    void update(int id, Game model);
}
