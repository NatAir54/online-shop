package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.entity.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> findAll();
    void add(Goods item);

    void update(Goods item, int id);

    void remove(int id);
}
