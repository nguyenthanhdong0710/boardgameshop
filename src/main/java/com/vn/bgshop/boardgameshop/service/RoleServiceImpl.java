package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
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
