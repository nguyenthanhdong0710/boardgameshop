package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleRepoImpl implements RoleRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        String query = "select r from Role r where r.name ='"+name+"'";
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery(query, Role.class);
        return roleTypedQuery.getResultList().isEmpty()?null:roleTypedQuery.getResultList().get(0);
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public void save(Role model) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Role model) {

    }
}
