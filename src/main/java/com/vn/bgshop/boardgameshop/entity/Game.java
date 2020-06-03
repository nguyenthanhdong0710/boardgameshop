package com.vn.bgshop.boardgameshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue
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

    @Column(name = "time_play")
    private int timeToPlay;

    @Column(name = "age_limited")
    private int ageLimited;

    @Column(name = "publisher",columnDefinition = "nvarchar(255)")
    private String publisher;

    @Column(name = "rules",columnDefinition = "nvarchar(255)")
    private String rules;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "image",columnDefinition = "nvarchar(255)")
    private String image;

    @Column(name = "is_del")
    private boolean status;

    /*
    *** @ManyToMany và @JoinTable xác định các thành phần tham gia vào liên kết Many to Many của 2 bảng User và Role.
        *** name: Tên của table trung gian
        *** joinColumns: Tên khóa chính của bảng này mà sẽ là khóa ngoại của bảng trung gian
        *** inverseJoinColumns: Tên khóa chính của bảng còn lại mà sẽ là khóa ngoại của bảng trung gian
    */

    /*This happens because you have a collection in your entity, and that collection has one or more items which are
    not present in the database. By specifying the above options you tell hibernate to save them to the database when
    saving their parent.*/
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "category_game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private Set<Category> categories;

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

    public int getTimeToPlay() {
        return timeToPlay;
    }

    public void setTimeToPlay(int timeToPlay) {
        this.timeToPlay = timeToPlay;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
