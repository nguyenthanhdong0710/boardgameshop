package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.Role;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


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
                OutputStream outStream = new FileOutputStream(new File(
                        "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\admin\\assets\\images\\games\\"
                                + gameImage));
                InputStream userAvatar = part.getInputStream();
                byte[] avatar = new byte[(int) part.getSize()];
                int nRead;
                while ((nRead = userAvatar.read(avatar, 0, avatar.length)) != -1) {
                    outStream.write(avatar, 0, nRead);
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

    @GetMapping("updategame/{id}")
    public String update(ModelMap model, @PathVariable int id) {
        Game game = gameService.findById(id);
        String[] gameCate = game.getCategoriesFMS();
        if(game != null){
            Set<Category> categories = game.getCategories();
           categories.forEach((category -> {
           }));
            model.addAttribute("game",game );
            model.addAttribute("categories",categoryService.findAll());
            return "admin/views/update";
        }else {
            return "admin/views/error-403";
        }
    }

    @PostMapping("update-game")
    public String update(Game game, @RequestParam("img") MultipartFile part, ModelMap model){
        try {
            String gameImage = part.getOriginalFilename();
            System.out.println(3);
            System.out.println(gameImage);
            System.out.println(1);
            if(gameImage != null || gameImage.trim().length()!=0){
                game.setImage(gameImage);
                //Xu ly copy anh
                OutputStream outStream = new FileOutputStream(new File(
                        "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\admin\\assets\\images\\games\\"
                                + gameImage));
                InputStream userAvatar = part.getInputStream();
                byte[] avatar = new byte[(int) part.getSize()];
                int nRead;
                while ((nRead = userAvatar.read(avatar, 0, avatar.length)) != -1) {
                    outStream.write(avatar, 0, nRead);
                }
            }else{
                game.setImage(gameService.findById(game.getId()).getImage());
            }
            String[] gameCategories = game.getCategoriesFMS();
            Set<Category> categories = new HashSet<>();
            for(String cateName : gameCategories){
                Category cate = categoryService.findByName(cateName);
                categories.add(cate);
            }
            game.setCategories(categories);
            gameService.save(game);
            model.addAttribute("messSucc","Update game successfully!");
            model.addAttribute("game", new Game());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("messFail","Update game failed!");
            model.addAttribute("game", new Game());
        }
        return "admin/views/update";
    }

    @PostMapping("quickupdate/{id}")
    public String quickUpdate(Game g, @PathVariable int id) {
        Game game = gameService.findById(id);
        game.setQuantity(g.getQuantity());
        game.setPrice(g.getPrice());
        gameService.save(game);
        return "redirect:/admin/allgame";
    }

    @PostMapping("deletegame/{id}")
    public String deleteGame(@PathVariable int id) {
        Game game = gameService.findById(id);
        game.setStatus(String.valueOf(new Date()));
        gameService.save(game);
        return "redirect:/admin/allgame";
    }

    @RequestMapping("allgame")
    public String updateDelete(ModelMap model) {
        model.addAttribute("games",gameService.findAll());
        return "admin/views/allgame";
    }
    @RequestMapping("all-deleted-game")
    public String allDeletedGame(ModelMap model) {
        model.addAttribute("games",gameService.findAllDeleted());
        model.addAttribute("isDelete", "Deleted");
        return "admin/views/allgame";
    }

    @RequestMapping("restore/{id}")
    public String restore(@PathVariable int id) {
        Game game = gameService.findById(id);
        game.setStatus(null);
        gameService.save(game);
        return "redirect:/admin/all-deleted-game";
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
