package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.User;
import java.util.List;


public interface UserService {

    List<User> findAll();

    User findByEmail(String email);

    User findByEmailNotBanned(String email);

    List<User> findByRole(String roleName);

    List<User> findByRoleSize(int roleSize);

    User findById(int id);

    void save(User model);

    void remove(int id);

    void update(int id, User model);
}
