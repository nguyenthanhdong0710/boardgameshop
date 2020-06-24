package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Category;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepoImpl implements CategoryRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category findByName(String category) {
        String query = "select c from Category c where c.name ='"+category+"'";
        TypedQuery<Category> categoryTypedQuery = entityManager.createQuery(query, Category.class);
        return categoryTypedQuery.getResultList().isEmpty()?null:categoryTypedQuery.getResultList().get(0);
    }

    @Override
    public List<Category> findAll() {
        String query = "select c from Category c";
        TypedQuery<Category> userTypedQuery = entityManager.createQuery(query, Category.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public Category findById(int id) {
        String query = "select c from Category c where c.id ='"+id+"'";
        TypedQuery<Category> categoryTypedQuery = entityManager.createQuery(query, Category.class);
        return categoryTypedQuery.getResultList().isEmpty()?null:categoryTypedQuery.getResultList().get(0);
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
