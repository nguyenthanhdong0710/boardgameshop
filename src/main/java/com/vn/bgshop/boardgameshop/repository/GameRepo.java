package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Game;

import java.util.List;


public interface GameRepo extends Repo<Game>{
    Game findByName(String name);

    List<Game> findAllDeleted();
}
