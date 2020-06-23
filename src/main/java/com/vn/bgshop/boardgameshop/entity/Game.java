package com.vn.bgshop.boardgameshop.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "games")

public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name",columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name = "min_player")
    private int minPlayer;

    @Column(name = "max_player")
    private int maxPlayer;

    @Column(name = "best_player_quantity")
    private int bestPlayerQuantity;

    @Column(name = "time_play_from")
    private int timeToPlayFrom;

    @Column(name = "time_play_to")
    private int timeToPlayTo;

    @Column(name = "age_limited")
    private int ageLimited;


    @Column(name = "publisher",columnDefinition = "nvarchar(255)")
    private String publisher;

    @Column(name = "rules",columnDefinition = "nvarchar(255)")
    private String rules;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "image",columnDefinition = "nvarchar(255)")
    private String image;

    @Column(name = "is_del")
    private String status;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "category_game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "game")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "game")
    private Set<CartDetail> cartDetails;


    @Transient
    private String[] categoriesFMS;

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public void setMinPlayer(int minPlayer) {
        this.minPlayer = minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public int getBestPlayerQuantity() {
        return bestPlayerQuantity;
    }

    public void setBestPlayerQuantity(int bestPlayerQuantity) {
        this.bestPlayerQuantity = bestPlayerQuantity;
    }

    public int getTimeToPlayFrom() {
        return timeToPlayFrom;
    }

    public void setTimeToPlayFrom(int timeToPlayFrom) {
        this.timeToPlayFrom = timeToPlayFrom;
    }

    public int getTimeToPlayTo() {
        return timeToPlayTo;
    }

    public void setTimeToPlayTo(int timeToPlayTo) {
        this.timeToPlayTo = timeToPlayTo;
    }

    public String getStatus() {
        return status;
    }

    public int getAgeLimited() {
        return ageLimited;
    }

    public void setAgeLimited(int ageLimited) {
        this.ageLimited = ageLimited;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String[] getCategoriesFMS() {
        return categoriesFMS;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }


    public void setCategoriesFMS(String[] categoriesFMS) {
        this.categoriesFMS = categoriesFMS;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minPlayer=" + minPlayer +
                ", maxPlayer=" + maxPlayer +
                ", bestPlayerQuantity=" + bestPlayerQuantity +
                ", timeToPlayFrom=" + timeToPlayFrom +
                ", timeToPlayTo=" + timeToPlayTo +
                ", ageLimited=" + ageLimited +
                ", publisher='" + publisher + '\'' +
                ", rules='" + rules + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
