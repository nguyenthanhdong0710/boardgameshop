package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.*;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class Cart {

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    private GameService gameService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderStatusService orderStatusService;


    @RequestMapping("cart")
    public String cart(ModelMap model, HttpSession session, @ModelAttribute(name = "mess") String mess) {
        List<CartDetail> cartDetails = (List<CartDetail>) session.getAttribute("cart");
        if (cartDetails.isEmpty()) {
            model.addAttribute("cartEmpty", "empty-cart.png");
        } else {
            int userId = ((User) session.getAttribute("loginedUser")).getId();
            double total = 0;
            for (CartDetail cartDetail : cartDetails) {
                total += cartDetail.getQuantity() * cartDetail.getGame().getPrice();
            }
            model.addAttribute("cartItems", cartDetails);
            model.addAttribute("total", total);
        }
        if (mess.trim().length() != 0) {
            model.addAttribute("mess", mess);
        } else {
            model.addAttribute("mess", null);
        }
        model.addAttribute("loginedUser", session.getAttribute("loginedUser"));
        return "user/cart/shoping-cart";
    }

    @GetMapping("add-to-cart")
    public String addToCart(@RequestParam(name = "gameId") int gameId,
                            @RequestParam(name = "category", defaultValue = "all") String categoryName,
                            @RequestParam(name = "page", defaultValue = "1") int page,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loginedUser");
        if (user != null) {
            Game game = gameService.findById(gameId).get();
            CartDetail cartDetail = cartDetailService.findByBothId(user.getId(), gameId);
            if (game.getQuantity() > 0) {
                if (cartDetail != null) {
                    if (cartDetail.getQuantity() < game.getQuantity()) {
                        cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                    } else {
                        redirectAttributes.addFlashAttribute("notEnough", "Sorry, we dont have enough game for you!");
                        System.out.println(123123);
                    }
                } else {
                    cartDetail = new CartDetail(user, game, game.getPrice(), 1, null);
                }
                cartDetailService.save(cartDetail);
                session.setAttribute("cart", cartDetailService.findByUserId(user.getId()));
            } else {
                redirectAttributes.addFlashAttribute("mess", "Sorry, we dont have enough "+game.getName()+" for you!");
            }
        } else {
            return "redirect:/home/login";
        }
        return "redirect:/shop?category="+categoryName+"&page="+(page+1);
    }

    @GetMapping("update-cart/{game-id}")
    public String updateCart(@PathVariable(name = "game-id") int gameId, @RequestParam("quantity") int quantity, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loginedUser");
        CartDetail cartDetail = cartDetailService.findByBothId(user.getId(), gameId);
        if (quantity <= gameService.findById(gameId).get().getQuantity()) {
            cartDetail.setQuantity(quantity);
            cartDetailService.save(cartDetail);
            session.setAttribute("cart", cartDetailService.findByUserId(user.getId()));
        } else {
            redirectAttributes.addFlashAttribute("mess", "Sorry, we dont have enough "+cartDetail.getGame().getName()+" for you!");
        }
        return "redirect:/cart";
    }

    @GetMapping("remove-cart/{game-id}")
    public String updateCart(@PathVariable(name = "game-id") int gameId, HttpSession session) {
        User user = (User) session.getAttribute("loginedUser");
        cartDetailService.remove(user.getId(), gameId);
        session.setAttribute("cart", cartDetailService.findByUserId(user.getId()));
        return "redirect:/cart";
    }

    @GetMapping("check-out")
    public String checkOut(HttpSession session,RedirectAttributes redirectAttributes) {
        Game game;
        double total = 0;
        User user = (User) session.getAttribute("loginedUser");
        List<CartDetail> cartDetails = (List<CartDetail>) session.getAttribute("cart");

        for (CartDetail cartDetail : cartDetails) {
            game = gameService.findById( cartDetail.getGame().getId()).get();
            if(cartDetail.getQuantity() > game.getQuantity()){
                redirectAttributes.addFlashAttribute("mess","Sorry, we dont have enough "+game.getName()+" for you!");
                return "redirect:/cart";
            }
        }

        OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_WAITING").get();
        Order order = orderService.saveAndFlush(new Order(user, new Date(), total, orderStatus));
        for (CartDetail cartDetail : cartDetails) {
            game = cartDetail.getGame();
            OrderDetail orderDetail = new OrderDetail(order, game, game.getPrice(), cartDetail.getQuantity(), null);
            orderDetailService.save(orderDetail);
            game.setQuantity(game.getQuantity() - cartDetail.getQuantity());
            gameService.save(game);
            total += cartDetail.getQuantity() * game.getPrice();
        }
        order.setTotal(total);
        orderService.update(order.getId(), order);
        cartDetailService.removeAllByUser(user.getId());
        session.setAttribute("cart", cartDetailService.findByUserId(user.getId()));
        session.setAttribute("games", gameService.findAll());
        redirectAttributes.addFlashAttribute("mess","Payment successfully!");
        return "redirect:/cart";
    }
}
