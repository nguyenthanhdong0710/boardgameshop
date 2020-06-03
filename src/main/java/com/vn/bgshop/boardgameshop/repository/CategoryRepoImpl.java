package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepoImpl implements CategoryRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category findByName(String Category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category model) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Category model) {

    }
}
