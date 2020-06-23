package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.RoleService;
import com.vn.bgshop.boardgameshop.service.RoleServiceImpl;
import com.vn.bgshop.boardgameshop.service.UserService;
import com.vn.bgshop.boardgameshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class Security {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("home/login")
    public String loginHome(ModelMap model, HttpSession session, @ModelAttribute("loginFail") String loginFail) {
        model.addAttribute("isLogin", false);
        model.addAttribute("games",(Page<Game>)  session.getAttribute("games"));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        model.addAttribute("categories",(List<Category>) session.getAttribute("cates"));
        if(loginFail.trim().length() != 0){
            model.addAttribute("messFail", "Email or password is invalid!");
        }
        return "user/views/shop-grid";
    }
    @GetMapping("home/login-fail")
    public String loginHomeFail(ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginFail","Email or password is invalid!");
        return "redirect:/home/login";
    }

    @GetMapping("register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "user/account/register";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @PostMapping(value = "register")
    public String register(User user, @RequestParam("ava") MultipartFile part,@RequestParam("passwordConfirm") String passConfirm, ModelMap model) {
        try {
            if (userService.findByEmail(user.getEmail()) == null) {
                if(passConfirm.equals(user.getPassword())){
                    String avatarName = part.getOriginalFilename();
                    if (avatarName == null || avatarName.trim().length() == 0) {
                        avatarName = "DEFAULT_AVATAR.png";
                    }
                    user.setAvatar(avatarName);
                    Role role = roleService.findByName("ROLE_USER");
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);
                    user.setRoles(roles);
                    userService.save(user);
                    //Xu ly copy anh
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
            return "user/account/register";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", new User());
            model.addAttribute("messFail", "Register failed!");
            return "user/account/register";
        }
    }

}
