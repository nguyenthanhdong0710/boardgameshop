package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GameRepoImpl implements GameRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Game findByName(String name) {
        return null;
    }

    @Override
    public List<Game> findAll() {
        return null;
    }

    @Override
    public Game findById(int id) {
        return null;
    }

    @Override
    public void save(Game model) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Game model) {

    }
}
