package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
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
        return null;
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
