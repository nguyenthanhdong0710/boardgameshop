package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("admin")
public class Admin {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")

    public String admin() {
        return "admin/views/index";
    }
    @GetMapping("addgame")
    public String insert(ModelMap model) {
        model.addAttribute("game", new Game());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/views/insert";
    }

    @PostMapping("addgame")
    public String insert(Game game, @RequestParam("img") MultipartFile part, ModelMap model){
        try {
            String gameImage = part.getOriginalFilename();
            game.setImage(gameImage);

            String[] gameCategories = game.getCategoriesFMS();
            Set<Category> categories = new HashSet<>();
            for(String cateName : gameCategories){
                Category cate = categoryService.findByName(cateName);
                categories.add(cate);
            }
            game.setCategories(categories);
            gameService.save(game);

            //Xu ly copy anh
            if(!gameImage.equalsIgnoreCase("DEFAULT_AVATAR.png")){
                OutputStream outStream = null;
                byte[] avatar = null;
                outStream = new FileOutputStream(new File(
                        "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\admin\\assets\\images\\games\\"
                                + gameImage));
                InputStream userAvatar = part.getInputStream();
                avatar = new byte[(int) part.getSize()];
                int nRead;
                while ((nRead = userAvatar.read(avatar, 0, avatar.length)) != -1) {
                    outStream.write(avatar, 0, nRead);
                }
            }
            model.addAttribute("messSucc","Insert game successfully!");
            model.addAttribute("game", new Game());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("messFail","Insert game failed!");
            model.addAttribute("game", new Game());
        }
        return "admin/views/insert";
    }
    @RequestMapping("allgame")
    public String updateDelete(ModelMap model) {
        model.addAttribute("games",gameService.findAll());
        return "admin/views/updateDelete";
    }

    @RequestMapping("alluser")
    public String allUser(ModelMap model) {
        List<User> list = new ArrayList<>();
        for(User user : userService.findAll()){
            Set<Role> rolesTest = user.getRoles();
            if(rolesTest.contains(roleService.findByName("ROLE_ADMIN"))) {
                user.setAdmin(true);
            }else {
                user.setAdmin(false);
            }
            list.add(user);
        }
        model.addAttribute("users",list);
        return "admin/views/setAdmin";
    }
    @PostMapping("set")
    public String setAdmin(int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN");
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin/alluser";
    }

    @PostMapping("remove")
    public String removeAdmin(int id) {
        User user = userService.findById(id);
        Role role = roleService.findByName("ROLE_ADMIN");
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin/alluser";
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
