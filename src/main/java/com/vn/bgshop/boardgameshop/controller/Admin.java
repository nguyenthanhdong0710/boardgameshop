package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("admin")
public class Admin {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("")
    public String admin() {
        return "admin/views/index";
    }
    @RequestMapping("addgame")
    public String insert() {
        return "admin/views/insert";
    }
    @RequestMapping("allgame")
    public String updateDelete() {
        return "admin/views/updateDelete";
    }

    @RequestMapping("alluser")
    public String allUser(ModelMap model) {
        model.addAttribute("users",userService.findAll());
        return "admin/views/setAdmin";
    }
    @RequestMapping("login")
    public String adminLogin() {
        return "admin/views/authentication-login";
    }

    @RequestMapping("register")
    public String adminRegister() {
        return "admin/views/authentication-register";
    }

    @RequestMapping("charts")
    public String charts() {
        return "admin/views/charts";
    }

    @RequestMapping("form-basic")
    public String formBasic() {
        return "admin/views/form-basic";
    }

    @RequestMapping("form-wizard")
    public String formWizard() {
        return "admin/views/form-wizard";
    }

    @RequestMapping("grid")
    public String grid() {
        return "admin/views/grid";
    }

    @RequestMapping("icon-fontawesome")
    public String iconFont() {
        return "admin/views/icon-fontawesome";
    }

    @RequestMapping("index2")
    public String index2() {
        return "admin/views/index2";
    }

    @RequestMapping("icon-material")
    public String iconMaterial() {
        return "admin/views/icon-material";
    }

    @RequestMapping("pages-buttons")
    public String pagesButtons() {
        return "admin/views/pages-buttons";
    }

    @RequestMapping("pages-calendar")
    public String pagesCalendar() {
        return "admin/views/pages-calendar";
    }

    @RequestMapping("pages-chat")
    public String pagesChat() {
        return "admin/views/pages-chat";
    }

    @RequestMapping("pages-elements")
    public String pagesElements() {
        return "admin/views/pages-elements";
    }

    @RequestMapping("pages-gallery")
    public String pagesGallery() {
        return "admin/views/pages-gallery";
    }

    @RequestMapping("pages-invoice")
    public String pagesInvoice() {
        return "admin/views/pages-invoice";
    }

    @RequestMapping("tables")
    public String tables() {
        return "admin/views/tables";
    }

    @RequestMapping("widgets")
    public String widgets() {
        return "admin/views/widgets";
    }

}
