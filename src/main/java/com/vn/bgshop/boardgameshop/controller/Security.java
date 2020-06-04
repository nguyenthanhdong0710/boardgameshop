package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.RoleService;
import com.vn.bgshop.boardgameshop.service.RoleServiceImpl;
import com.vn.bgshop.boardgameshop.service.UserService;
import com.vn.bgshop.boardgameshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;


@Controller
public class Security {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

/*    @PostMapping("login")
    public String login(HttpSession session, User user,ModelMap model) {
        User loginUser = userService.findByEmail(user.getEmail());
        if(loginUser != null){
            session.setAttribute("LOGINUSER",loginUser);
        }else{
            model.addAttribute("mess","Login failed! Your email or your password is incorrect!");
        }
        return "user/general";
    }*/

    @GetMapping("register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "user/account/register";
    }

    /*@GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
   *//*     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";*//*
    }*/

    @PostMapping(value = "register",produces = "application/x-www-form-urlencoded;charset=UTF-8")
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
                    System.out.println(user);

                    //Xu ly copy anh
                    if(!avatarName.equalsIgnoreCase("DEFAULT_AVATAR.png")){
                        OutputStream outStream = null;
                        byte[] avatar = null;
                        outStream = new FileOutputStream(new File(
                                "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\user\\img\\avatar\\"
                                        + avatarName));
                        InputStream userAvatar = part.getInputStream();
                        avatar = new byte[(int) part.getSize()];
                        int nRead;
                        while ((nRead = userAvatar.read(avatar, 0, avatar.length)) != -1) {
                            outStream.write(avatar, 0, nRead);
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
