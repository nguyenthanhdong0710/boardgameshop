package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.Category;



public interface CategoryRepo extends Repo<Category>{
    Category findByName(String Category);
}
