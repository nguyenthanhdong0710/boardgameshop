package com.vn.bgshop.boardgameshop.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_detail")
public class CartDetail implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cart_detail_status")
    private String status;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CartDetail() {
    }

    public CartDetail(User user, Game game, double price, int quantity, String status) {
        this.user = user;
        this.game = game;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "user=" + user +
                ", game=" + game +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
