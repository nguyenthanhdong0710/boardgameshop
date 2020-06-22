package com.vn.bgshop.boardgameshop.controller;

import com.vn.bgshop.boardgameshop.entity.*;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Controller
public class UserEdit {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private GameService gameService;

    @GetMapping("edit-information/{tabName}")
    public String editInfo(ModelMap model, HttpSession session, @PathVariable String tabName) {
        User user = (User) session.getAttribute("loginedUser");
        OrderStatus completed = orderStatusService.findByStatus("STATUS_RECEIVED").get();
        OrderStatus toPay = orderStatusService.findByStatus("STATUS_WAITING").get();
        OrderStatus toDelivery = orderStatusService.findByStatus("STATUS_PROCESSING").get();
        OrderStatus onDelivery = orderStatusService.findByStatus("STATUS_DELIVERING").get();
        OrderStatus toReturn = orderStatusService.findByStatus("STATUS_RETURN").get();
        OrderStatus completedFinal = orderStatusService.findByStatus("STATUS_FINAL").get();

        List<Order> historyOrder = new ArrayList<>();
        historyOrder.addAll( orderService.findByOrderStatusAndUser(completed,user));
        historyOrder.addAll( orderService.findByOrderStatusAndUser(toReturn,user));
        historyOrder.addAll( orderService.findByOrderStatusAndUser(completedFinal,user));

        List<Order> processingOrder = new ArrayList<>();
        processingOrder.addAll(orderService.findByOrderStatusAndUser(toPay,user));
        processingOrder.addAll(orderService.findByOrderStatusAndUser(toDelivery,user));
        processingOrder.addAll(orderService.findByOrderStatusAndUser(onDelivery,user));

        model.addAttribute("tabName", tabName);
        model.addAttribute("loginedUser", user);
        model.addAttribute("historyOrder", historyOrder);
        model.addAttribute("processingOrder", processingOrder);

        return "user/account/editInformation";
    }

    @PostMapping("change-avatar")
    public String changeAvatar(User user, @RequestParam("file") MultipartFile part, HttpSession session) {
        try {
            User updateUser = userService.findByEmail(user.getEmail());
            String avatar = part.getOriginalFilename();
            File ava = new File(
                    "E:\\OneDrive - Nguyen Sieu School\\Documents\\IntelliJProject\\boardgameshop\\src\\main\\resources\\static\\user\\img\\avatar\\"
                            + avatar);
            if (!ava.exists()) {
                part.transferTo(ava);
            }
            updateUser.setAvatar(avatar);
            userService.update(updateUser.getId(), updateUser);
            session.setAttribute("loginedUser", updateUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/edit-information/basic-information";
    }

    @PostMapping("update-information")
    public String updateInformation(User user, HttpSession session, String originalEmail) {
        User updateUser = userService.findByEmail(originalEmail);
        updateUser.setUserName(user.getUserName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        userService.update(updateUser.getId(), updateUser);
        session.setAttribute("loginedUser", updateUser);
        return "redirect:/edit-information/basic-information";
    }

    @PostMapping("change-password")
    public String changePassword(User user, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, HttpSession session) {
        User updateUser = userService.findByEmail(user.getEmail());
        System.out.println(123);
        System.out.println(updateUser);
        System.out.println(123);
        if (new BCryptPasswordEncoder().matches(user.getPassword(), updateUser.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                updateUser.setPassword(newPassword);
                userService.save(updateUser);
            } else {
                //Your confirm password is not matches your new password
            }
        } else {
            //Your password is incorect
        }
        return "redirect:/edit-information/change-password";
    }

    @GetMapping("/cancel-order")
    public String cancelOrder(@RequestParam("orderId") int orderId) {
        Order order = orderService.findById(orderId).get();
        OrderStatus cancelled = orderStatusService.findByStatus("STATUS_CANCELLED").get();
        order.setOrderStatus(cancelled);
        Set<OrderDetail> orderDetailList = order.getOrderDetails();
        orderDetailList.forEach(new Consumer<OrderDetail>() {
            @Override
            public void accept(OrderDetail orderDetail) {
                Game game = orderDetail.getGame();
                game.setQuantity(game.getQuantity() + orderDetail.getQuantity());
                gameService.save(game);
            }
        });
        orderService.save(order);
        return "redirect:/edit-information/processing-order";
    }

    @GetMapping("/return-order")
    public String returnlOrder(@RequestParam("orderId") int orderId) {
        Order order = orderService.findById(orderId).get();
        OrderStatus cancelled = orderStatusService.findByStatus("STATUS_RETURN").get();
        order.setOrderStatus(cancelled);
        orderService.save(order);
        return "redirect:/edit-information/oder-history";
    }
}
