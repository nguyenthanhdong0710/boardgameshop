package com.vn.bgshop.boardgameshop.repository;
import java.util.List;

public interface Repo<T>  {
    List<T> findAll();

    T findById(int id);

    void save(T model);

    void remove(int id);

    void update(int id, T model);
}

