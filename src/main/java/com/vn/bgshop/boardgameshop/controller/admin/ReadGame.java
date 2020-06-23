package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.User;
import com.vn.bgshop.boardgameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReadGame {

    @Autowired
    private GameService gameService;

    @RequestMapping("admin/allgame")
    public String updateDelete(ModelMap model,HttpSession session) {
        model.addAttribute("games",gameService.findAll());
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allgame";
    }

    @RequestMapping("admin/all-deleted-game")
    public String allDeletedGame(ModelMap model, HttpSession session) {
        model.addAttribute("games",gameService.findAllDeleted());
        model.addAttribute("isDelete", "Deleted");
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allgame";
    }
}
