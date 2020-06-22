package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReadGame {

    @Autowired
    private GameService gameService;

    @RequestMapping("admin/allgame")
    public String updateDelete(ModelMap model) {
        model.addAttribute("games",gameService.findAll());
        return "admin/views/allgame";
    }

    @RequestMapping("admin/all-deleted-game")
    public String allDeletedGame(ModelMap model) {
        model.addAttribute("games",gameService.findAllDeleted());
        model.addAttribute("isDelete", "Deleted");
        return "admin/views/allgame";
    }
}
