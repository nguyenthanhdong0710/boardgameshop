package com.vn.bgshop.boardgameshop.controllers;

import com.vn.bgshop.boardgameshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Login {
    @PostMapping("login")
    public String login(User user) {

        if(user.getEmail().equals("123@123") && user.getPassword().equals("123")){
            return "user/general";
        }
        return "user/views/index";
    }
}
