package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.CategoryService;
import com.vn.bgshop.boardgameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CreateGame {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("admin/addgame")
    public String insert(ModelMap model, HttpSession session) {
        model.addAttribute("game", new Game());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/insert";
    }


    @PostMapping("admin/addgame")
    public String insert(Game game, @RequestParam("img") MultipartFile part, ModelMap model,HttpSession session){
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
            //Xu ly copy anh
            File ava = new File(
                    "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\user\\img\\games\\"
                            + gameImage);
            if(!ava.exists()){
                part.transferTo(ava);
            }
            gameService.save(game);
            model.addAttribute("categories",categoryService.findAll());
            model.addAttribute("messSucc","Insert game successfully!");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("categories",categoryService.findAll());
            model.addAttribute("messFail","Insert game failed!");
        }
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/insert";
    }
}
