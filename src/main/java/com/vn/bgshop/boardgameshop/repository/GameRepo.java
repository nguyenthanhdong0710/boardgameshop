package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Game;


public interface GameRepo extends Repo<Game>{
    Game findByName(String name);
}
