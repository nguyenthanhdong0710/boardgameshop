package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.RoleService;
import com.vn.bgshop.boardgameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserHandle {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("admin/all-user")
    public String allUser(ModelMap model,HttpSession session) {
        model.addAttribute("users",userService.findByRoleSize(1));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
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
        model.addAttribute("loginedUser", loginedUser);
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

    @GetMapping("admin/remove-manager")
    public String removeManager(@RequestParam("id") int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN_MANAGER");
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userService.update(id,user);
        return "redirect:/admin/employee";
    }

    @GetMapping("admin/add-employee")
    public String addAdmin(ModelMap model,HttpSession session) {
        model.addAttribute("user", new User());
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/insertAdmin";
    }

    @PostMapping("admin/addemployee")
    public String register(User user, @RequestParam("ava") MultipartFile part, @RequestParam("passwordConfirm") String passConfirm, ModelMap model,HttpSession session) {
        try {
            if (userService.findByEmail(user.getEmail()) == null) {
                if(passConfirm.equals(user.getPassword())){
                    String avatarName = part.getOriginalFilename();
                    if (avatarName == null || avatarName.trim().length() == 0) {
                        avatarName = "DEFAULT_AVATAR.png";
                    }
                    user.setAvatar(avatarName);
                    Role roleUser = roleService.findByName("ROLE_USER");
                    Role roleEmployee = roleService.findByName("ROLE_ADMIN_STAFF");
                    Set<Role> roles = new HashSet<>();
                    roles.add(roleUser);
                    roles.add(roleEmployee);
                    if(user.isManager()){
                        Role roleManager = roleService.findByName("ROLE_ADMIN_MANAGER");
                        roles.add(roleManager);
                    }
                    user.setRoles(roles);
                    userService.save(user);
                    if(!avatarName.equalsIgnoreCase("DEFAULT_AVATAR.png")){
                        File ava = new File(
                                "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\user\\img\\avatar\\"
                                        + avatarName);
                        if(!ava.exists()){
                            part.transferTo(ava);
                        }
                    }
                    model.addAttribute("user", new User());
                    model.addAttribute("messSucc", "Register sucessfully!");
                }else {
                    model.addAttribute("user", new User());
                    model.addAttribute("messFail", "Password comfirm is not correct!");
                }
            } else {
                model.addAttribute("user", new User());
                model.addAttribute("messFail", "Email is already exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", new User());
            model.addAttribute("messFail", "Register failed!");

        }
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/insertAdmin";
    }

}
