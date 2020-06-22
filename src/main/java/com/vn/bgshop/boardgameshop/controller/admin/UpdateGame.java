package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.service.CategoryService;
import com.vn.bgshop.boardgameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UpdateGame {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("admin/updategame/{id}")
    public String update(ModelMap model, @PathVariable int id) {
        Game game = gameService.findById(id).get();
        if(game != null){
            model.addAttribute("game",game );
            model.addAttribute("categories",categoryService.findAll());
            return "admin/views/update";
        }else {
            return "admin/views/error-403";
        }
    }

    @PostMapping("admin/update-game")
    public String update(Game game , @RequestParam("img") MultipartFile part, ModelMap model){
        try {
            String gameImage = part.getOriginalFilename();
            if (!part.isEmpty()) {
                File ava = new File(
                        "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\user\\img\\games\\"
                                + gameImage);
                if(!ava.exists()){
                    part.transferTo(ava);
                }
                game.setImage(gameImage);
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
            model.addAttribute("game", gameService.findById(game.getId()));
            model.addAttribute("categories",categoryService.findAll());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("messFail","Update game failed!");
            model.addAttribute("game", gameService.findById(game.getId()));
            model.addAttribute("categories",categoryService.findAll());
        }
        return "admin/views/update";
    }

    @PostMapping("admin/quickupdate/{id}")
    public String quickUpdate(Game g, @PathVariable int id) {
        Game game = gameService.findById(id).get();
        game.setQuantity(g.getQuantity());
        game.setPrice(g.getPrice());
        gameService.save(game);
        return "redirect:/admin/allgame";
    }

    @RequestMapping("admin/restore/{id}")
    public String restore(@PathVariable int id) {
        Game game = gameService.findById(id).get();
        game.setStatus(null);
        gameService.save(game);
        return "redirect:/admin/all-deleted-game";
    }
}
