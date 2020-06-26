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
    public String changeStatus(ModelMap model, HttpSession session,
                               @RequestParam("changeStatus") String changeStatus,
                               @RequestParam("id") String orderId,
                               @RequestParam("cancel") boolean cancel) {
        model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
        OrderStatus orderStatus = orderStatusService.findByStatus("STATUS_CANCELLED").get();
        Order order = orderService.findById(Integer.parseInt(orderId)).get();
        switch (changeStatus){
            case "Take order":
                if(!cancel){
                    orderStatus = orderStatusService.findByStatus("STATUS_PROCESSING").get();
                }
                order.setOrderStatus(orderStatus);
                orderService.save(order);
                return "redirect:/admin/to-pay-order";
            case "On delivery":
                if(!cancel){
                    orderStatus = orderStatusService.findByStatus("STATUS_DELIVERING").get();
                }
                order.setOrderStatus(orderStatus);
                orderService.save(order);
                return "redirect:/admin/to-delivery-order";
            case "Received":
                if(!cancel){
                    orderStatus = orderStatusService.findByStatus("STATUS_RECEIVED").get();
                }
                order.setOrderStatus(orderStatus);
                orderService.save(order);
                return "redirect:/admin/on-delivery-order";
            case "Accept return":
                if(!cancel){
                    orderStatus = orderStatusService.findByStatus("STATUS_RETURNED").get();
                }
                order.setOrderStatus(orderStatus);
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
                return "redirect:/admin/to-return-order";
            case "Deny":
                if(!cancel){
                    orderStatus = orderStatusService.findByStatus("STATUS_FINAL").get();
                }
                order.setOrderStatus(orderStatus);
                orderService.save(order);
                return "redirect:/admin/to-return-order";
            default:
                return "redirect:/admin/all-order";
        }
/*
        if(changeStatus.equals("Take order")){
            if(!cancel){
                orderStatus = orderStatusService.findByStatus("STATUS_PROCESSING").get();
            }
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-pay-order";
        }
        if(changeStatus.equals("On delivery")){
            if(!cancel){
                orderStatus = orderStatusService.findByStatus("STATUS_DELIVERING").get();
            }
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-delivery-order";
        }
        if(changeStatus.equals("Received")){
            if(!cancel){
                orderStatus = orderStatusService.findByStatus("STATUS_RECEIVED").get();
            }
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/on-delivery-order";
        }
        if(changeStatus.equals("Accept return")){
            if(!cancel){
                orderStatus = orderStatusService.findByStatus("STATUS_RETURNED").get();
            }
            order.setOrderStatus(orderStatus);
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
            if(!cancel){
                orderStatus = orderStatusService.findByStatus("STATUS_FINAL").get();
            }
            order.setOrderStatus(orderStatus);
            orderService.save(order);
            model.addAttribute("loginedUser", (User) session.getAttribute("loginedUser"));
            return "redirect:/admin/to-return-order";
        }
        return "redirect:/admin/all-order";*/
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
        model.addAttribute("changeStatus","Received");
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
