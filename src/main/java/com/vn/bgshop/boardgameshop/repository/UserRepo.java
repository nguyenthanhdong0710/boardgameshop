package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.User;


public interface UserRepo extends Repo<User>{
    User findByEmail(String email);
}
