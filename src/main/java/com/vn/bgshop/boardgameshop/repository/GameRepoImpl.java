package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        String query = "select g from Game g";
        TypedQuery<Game> userTypedQuery = entityManager.createQuery(query, Game.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public Game findById(int id) {
        return null;
    }

    @Override
    public void save(Game model) {
        if (String.valueOf(model.getId()) != null) {
            //update
            entityManager.merge(model);
        }else {
            //add new
            entityManager.persist(model);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Game model) {

    }
}
