package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.repository.GameRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepo gameRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Game findByName(String name) {
        return null;
    }


    @Override
    public Page<Game> findAll(Pageable pageable) {
        return gameRepo.findAll(pageable);
    }

    @Override
    public Page<Game> findGamesByCategory(String cateName, Pageable pageable) {
        return gameRepo.findGamesByCategory(cateName, pageable);
    }

    @Override
    public Page<Game> search(String terms, int limit, int offset) throws InterruptedException {
   /*     FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        QueryBuilder queryBuilder = (QueryBuilder) fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Game.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(terms)
                .createQuery();

        // wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);

        jpaQuery.setMaxResults(limit);
        jpaQuery.setFirstResult(offset);

        // execute search
        return jpaQuery.getResultList();*/
        return null;
    }

    @Override
    public List<Game> findAll() {
        return gameRepo.findAll();
    }


    public List<Game> findAll(String category) {
        return gameRepo.findAll();
    }

    @Override
    public List<Game> findAllDeleted() {
        return gameRepo.findAllDeleted();
    }

    @Override
    public Optional<Game> findById(int id) {
        return gameRepo.findById(new Integer(id));
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
