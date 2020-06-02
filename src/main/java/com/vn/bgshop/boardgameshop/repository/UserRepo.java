package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Repository;


public interface UserRepo extends Repo<User>{
    User findByEmail(String email);
}
