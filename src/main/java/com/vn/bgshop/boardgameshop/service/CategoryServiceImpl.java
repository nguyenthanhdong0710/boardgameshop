package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.Category;
import com.vn.bgshop.boardgameshop.entity.Game;
import com.vn.bgshop.boardgameshop.repository.CategoryRepo;
import com.vn.bgshop.boardgameshop.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category findByName(String name) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category model) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, Category model) {

    }
}
