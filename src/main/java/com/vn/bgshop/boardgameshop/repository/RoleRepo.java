package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Role;

public interface RoleRepo extends Repo<Role> {
    Role findByName(String name);
}
