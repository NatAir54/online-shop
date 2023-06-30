package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.jdbc.GoodsDao;
import com.studying.onlineshop.entity.Goods;
import java.time.LocalDateTime;
import java.util.List;

public class GoodsService {
    private GoodsDao jdbcGoods;

    public GoodsService(GoodsDao jdbcGoods) {
        this.jdbcGoods = jdbcGoods;
    }

    public List<Goods> findAll() {
        return jdbcGoods.findAll();
    }

    public void add(Goods item) {
        item.setDate(LocalDateTime.now());
        jdbcGoods.add(item);
    }

    public void update(Goods item, int id) {
        item.setDate(LocalDateTime.now());
        jdbcGoods.update(item, id);
    }

    public void remove(int id) {
        jdbcGoods.remove(id);
    }
}
