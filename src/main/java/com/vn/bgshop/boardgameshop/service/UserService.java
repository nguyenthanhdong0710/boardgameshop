package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User findByEmail(String email);

    List<User> findAll();

    User findById(int id);

    void save(User model);

    void remove(int id);

    void update(int id, User model);
}
