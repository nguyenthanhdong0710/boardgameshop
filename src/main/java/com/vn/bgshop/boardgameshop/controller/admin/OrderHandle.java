package com.vn.bgshop.boardgameshop.controller.admin;

import com.vn.bgshop.boardgameshop.entity.*;
import com.vn.bgshop.boardgameshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.function.Consumer;

@Controller
public class OrderHandle {

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private GameService gameService;

    @GetMapping("admin/change-status")
    public String changeStatus(ModelMap model, @RequestParam("changeStatus") String changeStatus, @RequestParam("id") String orderId,HttpSession session) {
        if(changeStatus.equals("Take order")){
            Order order = orderService.findById(Integer.parseInt(orderId)).get();
            OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_PROCESSING").get();
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-pay-order";
        }
        if(changeStatus.equals("On delivery")){
            Order order = orderService.findById(Integer.parseInt(orderId)).get();
            OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_DELIVERING").get();
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-delivery-order";
        }
        if(changeStatus.equals("Complete")){
            Order order = orderService.findById(Integer.parseInt(orderId)).get();
            OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_RECEIVED").get();
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/on-delivery-order";
        }
        if(changeStatus.equals("Accept return")){
            Order order = orderService.findById(Integer.parseInt(orderId)).get();
            OrderStatus returned = orderStatusService.findByStatus("STATUS_RETURNED").get();
            order.setOrderStatus(returned);
            Set<OrderDetail> orderDetails = order.getOrderDetails();
            orderDetails.forEach(new Consumer<OrderDetail>() {
                @Override
                public void accept(OrderDetail orderDetail) {
                    Game game = orderDetail.getGame();
                    game.setQuantity(game.getQuantity() + orderDetail.getQuantity());
                    gameService.save(game);
                }
            });
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-return-order";
        }
        if(changeStatus.equals("denied")){
            Order order = orderService.findById(Integer.parseInt(orderId)).get();
            OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_FINAL").get();
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-return-order";
        }
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "redirect:/admin/all-order";
    }

    @GetMapping("admin/all-order")
    public String allOrder(ModelMap model,HttpSession session) {
        model.addAttribute("orders",orderService.findAll());
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allorder";
    }

    @GetMapping("admin/to-pay-order")
    public String allToPayOrder(ModelMap model,HttpSession session) {
        OrderStatus toPay = orderStatusService.findByStatus("STATUS_WAITING").get();
        model.addAttribute("changeStatus","Take order");
        model.addAttribute("orders",orderService.findByOrderStatus(toPay));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allorder";
    }

    @GetMapping("admin/to-delivery-order")
    public String allToDeliveryOrder(ModelMap model,HttpSession session) {
        OrderStatus toPay = orderStatusService.findByStatus("STATUS_PROCESSING").get();
        model.addAttribute("changeStatus","On delivery");
        model.addAttribute("orders",orderService.findByOrderStatus(toPay));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allorder";
    }

    @GetMapping("admin/on-delivery-order")
    public String allOnDeliveryOrder(ModelMap model,HttpSession session) {
        OrderStatus toPay = orderStatusService.findByStatus("STATUS_DELIVERING").get();
        model.addAttribute("changeStatus","Complete");
        model.addAttribute("orders",orderService.findByOrderStatus(toPay));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allorder";
    }

    @GetMapping("admin/to-return-order")
    public String allCompletedOrder(ModelMap model, HttpSession session) {
        OrderStatus toPay = orderStatusService.findByStatus("STATUS_RETURN").get();
        model.addAttribute("changeStatus","Accept return");
        model.addAttribute("orders",orderService.findByOrderStatus(toPay));
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        return "admin/views/allorder";
    }
}
