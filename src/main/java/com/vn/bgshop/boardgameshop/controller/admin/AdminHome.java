package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AdminHome {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("admin")
    public String admin() {
        return "admin/views/index";
    }

    @RequestMapping("admin/all-user")
    public String allUser(ModelMap model) {
        model.addAttribute("users",userService.findByRoleSize(1));
        return "admin/views/alluser";
    }

    @RequestMapping("admin/employee")
    public String allAdmin(ModelMap model,HttpSession session) {
        List<User> list = userService.findByRole("ROLE_ADMIN_STAFF");
        User loginedUser = (User)session.getAttribute("loginedUser");
        Role manager = roleService.findByName("ROLE_ADMIN_MANAGER");
        for(User user : list){
            if(loginedUser.getId() == user.getId()){
                list.remove(user);
                break;
            }
        }
        for(User user : list){
            user.setManager(user.getRoles().contains(manager));
        }
        model.addAttribute("users",list);
        return "admin/views/alladmin";
    }

    @GetMapping("admin/ban-user")
    public String banUser(@RequestParam("id") int id,@RequestParam("url") String url, HttpSession session) {
        User user = userService.findById(id);
        User admin = (User) session.getAttribute("loginedUser");
        String statusDetail = "Banned by: "+admin.getUserName()+"("+admin.getId()+")"+"/ At:"+new Date();
        user.setStatus(statusDetail);
        userService.update(id,user);
        return url.equals("user")?"redirect:/admin/all-user":"redirect:/admin/employee";
    }

    @GetMapping("admin/active-user")
    public String activeUser(@RequestParam("id") int id,@RequestParam("url") String url, HttpSession session) {
        User user = userService.findById(id);
        User admin = (User) session.getAttribute("loginedUser");
        user.setStatus(null);
        userService.update(id,user);
        return url.equals("user")?"redirect:/admin/all-user":"redirect:/admin/employee";
    }


    @GetMapping("admin/be-manager")
    public String beManager(@RequestParam("id") int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN_MANAGER");
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userService.update(id,user);
        return "redirect:/admin/employee";
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
