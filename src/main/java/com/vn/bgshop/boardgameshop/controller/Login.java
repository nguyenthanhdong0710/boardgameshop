package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.Admin;
import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Login {
    @PostMapping("login")
    public String login(User user, Admin user1) {
        System.out.println(user.getEmail());
        System.out.println(user1.getEmail1());
        if(user.getEmail().equals("123@123") && user.getPassword().equals("123")){
            return "user/general";

        }
        return "user/views/index";
    }
}
