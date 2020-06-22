package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.*;
import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("admin/alluser")
    public String allUser(ModelMap model) {
        List<User> list = new ArrayList<>();
        for(User user : userService.findAll()){
            Set<Role> rolesTest = user.getRoles();
            if(rolesTest.contains(roleService.findByName("ROLE_ADMIN_STAFF"))) {
                user.setAdmin(true);
            }else {
                user.setAdmin(false);
            }
            list.add(user);
        }
        model.addAttribute("users",list);
        return "admin/views/alluser";
    }

    @RequestMapping("admin/employee")
    public String allAdmin(ModelMap model) {
        model.addAttribute("users",userService.findByRole("ROLE_ADMIN_STAFF"));
        return "admin/views/alladmin";
    }

    @PostMapping("admin/set")
    public String setAdmin(int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN_STAFF");
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userService.update(id,user);
        return "redirect:/admin/alluser";
    }

    @PostMapping("admin/remove")
    public String removeAdmin(int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN_STAFF");
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userService.update(id,user);
        return "redirect:/admin/alluser";
    }

    @GetMapping("admin/remove")
    public String removeAdmin1(@RequestParam("id") int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN_STAFF");
        Set<Role> roles = user.getRoles();
        roles.remove(role);
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
