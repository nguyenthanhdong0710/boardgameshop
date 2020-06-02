package com.vn.bgshop.boardgameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Home {
    @RequestMapping("home")
    public String home() {
        return "user/views/index";
    }

    @RequestMapping("")
    public String home1() {
        return "user/views/index";
    }

    @RequestMapping("contact")
    public String contact() {
        return "user/views/contact";
    }

    @RequestMapping("cart")
    public String cart() {
        return "user/cart/shoping-cart";
    }

    @RequestMapping("shop")
    public String shop() {
        return "user/views/shop-grid";
    }

    @RequestMapping("shop-details")
    public String shopDetails() {
        return "user/views/shop-details";
    }



    @RequestMapping("403")
    public String error() {
        return "admin/views/error-403";
    }

}
