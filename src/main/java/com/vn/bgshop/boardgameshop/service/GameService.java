package com.vn.bgshop.boardgameshop.service;


import com.vn.bgshop.boardgameshop.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface GameService {

    Game findByName(String name);

    List<Game> findAll();

    Page<Game> findAll(Pageable pageable);

    Page<Game> findGamesByCategory(String cateName, Pageable pageable);

    Page<Game> search(String terms, int limit, int offset) throws InterruptedException;

    List<Game> findAllDeleted();

    Optional<Game> findById(int id);

    void save(Game model);

    void remove(int id);

    void update(int id, Game model);
}
