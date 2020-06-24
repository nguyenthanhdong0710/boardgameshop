package com.vn.bgshop.boardgameshop.controller.admin;


import com.vn.bgshop.boardgameshop.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
public class AdminHome {

    @RequestMapping("admin")
    public String admin(ModelMap model, HttpSession session) {
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/index";
    }

    @RequestMapping("admin/login")
    public String adminLogin() {
        return "admin/views/unused/authentication-login";
    }

    @RequestMapping("admin/register")
    public String adminRegister() {
        return "admin/views/unused/authentication-register";
    }

    @RequestMapping("admin/charts")
    public String charts() {
        return "admin/views/unused/charts";
    }

    @RequestMapping("admin/form-basic")
    public String formBasic() {
        return "admin/views/unused/form-basic";
    }

    @RequestMapping("admin/form-wizard")
    public String formWizard() {
        return "admin/views/unused/form-wizard";
    }

    @RequestMapping("admin/grid")
    public String grid() {
        return "admin/views/unused/grid";
    }

    @RequestMapping("admin/icon-fontawesome")
    public String iconFont() {
        return "admin/views/unused/icon-fontawesome";
    }

    @RequestMapping("admin/index2")
    public String index2() {
        return "admin/views/unused/index2";
    }

    @RequestMapping("admin/icon-material")
    public String iconMaterial() {
        return "admin/views/unused/icon-material";
    }

    @RequestMapping("admin/pages-buttons")
    public String pagesButtons() {
        return "admin/views/unused/pages-buttons";
    }

    @RequestMapping("admin/pages-calendar")
    public String pagesCalendar() {
        return "admin/views/unused/pages-calendar";
    }

    @RequestMapping("admin/pages-chat")
    public String pagesChat() {
        return "admin/views/unused/pages-chat";
    }

    @RequestMapping("admin/pages-elements")
    public String pagesElements() {
        return "admin/views/unused/pages-elements";
    }

    @RequestMapping("admin/pages-gallery")
    public String pagesGallery() {
        return "admin/views/unused/pages-gallery";
    }

    @RequestMapping("admin/pages-invoice")
    public String pagesInvoice() {
        return "admin/views/unused/pages-invoice";
    }

    @RequestMapping("admin/tables")
    public String tables() {
        return "admin/views/unused/tables";
    }

    @RequestMapping("admin/widgets")
    public String widgets() {
        return "admin/views/unused/widgets";
    }

}
