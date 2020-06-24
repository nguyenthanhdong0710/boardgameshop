package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

@Controller
public class DeleteGame {

    @Autowired
    private GameService gameService;

    @PostMapping("admin/deletegame/{id}")
    public String deleteGame(@PathVariable int id) {
        Game game = gameService.findById(id).get();
        game.setStatus(String.valueOf(new Date()));
        gameService.save(game);
        return "redirect:/admin/allgame";
    }
}
