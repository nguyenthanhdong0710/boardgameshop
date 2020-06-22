/*
package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepoImpl implements OrderRepo {

    @PersistenceContext
    private EntityManager entityManager;


    
    @Override
    public List<Order> findAll() {
        String query = "select u from Order u";
        TypedQuery<Order> orderTypedQuery = entityManager.createQuery(query, Order.class);
        return orderTypedQuery.getResultList();
    }

    @Override
    public List<Order> findByUser(int userId) {
        String query = "select u from Order u where u.user.id ='"+userId+"'";
        TypedQuery<Order> orderTypedQuery = entityManager.createQuery(query, Order.class);
        return orderTypedQuery.getResultList();
    }

    @Override
    public Order findById(int id) {
        String query = "select u from Order u where u.id ='"+id+"'";
        TypedQuery<Order> orderTypedQuery = entityManager.createQuery(query, Order.class);
        return orderTypedQuery.getResultList().isEmpty()?null:orderTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(Order model) {
        if (String.valueOf(model.getId()) != null) {
            //update
            entityManager.merge(model);
        }else {
            //add new
            entityManager.persist(model);
        }
    }



}
*/
