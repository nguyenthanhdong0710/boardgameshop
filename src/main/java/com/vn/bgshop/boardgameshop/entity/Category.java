package com.vn.bgshop.boardgameshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name",columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name = "decription",columnDefinition = "nvarchar(max)")
    private String decription;

    @ManyToMany(mappedBy = "games")
    private Set<Game> games;

    public Category() {
    }

}
