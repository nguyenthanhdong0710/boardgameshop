package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findByName(String name);

    List<Category> findAll();

    Category findById(int id);

    void save(Category model);

    void remove(int id);

    void update(int id, Category model);
}
