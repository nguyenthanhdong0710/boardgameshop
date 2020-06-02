package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String name);

    List<Role> findAll();

    Role findById(int id);

    void save(Role model);

    void remove(int id);

    void update(int id, Role model);
}
