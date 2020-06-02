package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        String query = "select u from User u where u.email ='"+email+"'";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        System.out.println(userTypedQuery.getResultList());
        if(!userTypedQuery.getResultList().isEmpty()){
            return userTypedQuery.getResultList().get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        String query = "select u from User u";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public User findById(int id) {
        String query = "select u from User u where u.id ='"+id+"'";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query, User.class);
        return userTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(User model) {
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
    public void update(int id, User model) {

    }
}
