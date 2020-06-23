package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.*;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.*;


@Controller
public class Home {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GameService gameService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderStatusService orderStatusService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = {"", "home", "shop"})
    public String home(ModelMap model,
                       HttpSession session,
                       @RequestParam(name = "category", defaultValue = "all") String categoryName,
                       @RequestParam(name = "page", defaultValue = "1") int page,
                       @ModelAttribute("mess") String mess) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Game> gamePage = gameService.findAll(pageable);
        if (!categoryName.equals("all")) {
            gamePage = gameService.findGamesByCategory(categoryName, pageable);
        }
        session.setAttribute("cates", categoryService.findAll());
        session.setAttribute("games", gamePage);
        session.setAttribute("totalPage", gamePage.getTotalPages());
        int totalPages = gamePage.getTotalPages();
        List<Integer> list = new ArrayList<>();
        if (totalPages > 0) {
            for (int i = 0; i < totalPages; i++) {
                list.add(i);
            }
            model.addAttribute("pageNumbers", list);
        }
        User loginedUser = getLoginedUser();
        if (loginedUser == null) {
            session.setAttribute("loginedUser", null);
        } else {
            session.setAttribute("loginedUser", loginedUser);
            session.setAttribute("cart", cartDetailService.findByUserId(loginedUser.getId()));
        }
        if (mess.trim().length() != 0) {
            model.addAttribute("mess", mess);
        } else {
            model.addAttribute("mess", null);
        }
        List<Order> listOrders = orderService.findAll();
        for (Order order : listOrders) {
            if ((new Date().getTime() - order.getSaleDate().getTime()) >= 7 * 24 * 60 * 60 * 1000) {
                order.setOrderStatus(orderStatusService.findByStatus("STATUS_FINAL").get());
                orderService.save(order);
            }
        }

        System.out.println(randomPass(20));
        Locale locale = new Locale("en", "UK");
        System.out.println(locale.getLanguage());
        model.addAttribute("category", categoryName);
        model.addAttribute("games", (Page<Game>) session.getAttribute("games"));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        model.addAttribute("categories", (List<Category>) session.getAttribute("cates"));
        return "user/views/shop-grid";
    }

    @RequestMapping("search")
    public String search(@RequestParam("key-words") String keyWords, ModelMap model, HttpSession session) {

        model.addAttribute("games", (Page<Game>) session.getAttribute("games"));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        model.addAttribute("categories", (List<Category>) session.getAttribute("cates"));
        return "user/views/shop-grid";
    }

    @RequestMapping("contact")
    public String contact(ModelMap model, HttpSession session) {
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        model.addAttribute("categories", (List<Category>) session.getAttribute("cates"));
        return "user/views/contact";
    }


    @RequestMapping("shop-details")
    public String shopDetails(ModelMap model, HttpSession session) {
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "user/views/shop-details";
    }

    @RequestMapping("403")
    public String error() {
        return "user/views/error-403";
    }

    private User getLoginedUser() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User loginedUser = userService.findByEmail(username);
        if (loginedUser != null) {
            Set<Role> roles = loginedUser.getRoles();
            if (roles.contains(roleService.findByName("ROLE_ADMIN_STAFF"))) {
                loginedUser.setAdmin(true);
            } else {
                loginedUser.setAdmin(false);
            }
            if (roles.contains(roleService.findByName("ROLE_ADMIN_MANAGER"))) {
                loginedUser.setManager(true);
            } else {
                loginedUser.setManager(false);
            }
        }
        return loginedUser;
    }

    private String randomPass(int size) {
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (size > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (size > 0)) {

                r.append(AlphaNumericString.charAt(k));
                size--;
            }
        }

        // return the resultant string
        return r.toString();
    }

}
