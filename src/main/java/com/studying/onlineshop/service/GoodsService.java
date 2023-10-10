package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.GoodsDao;
import com.studying.onlineshop.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GoodsService {
    @Autowired
    private GoodsDao JDBC_GOODS;

    public GoodsService() {
    }

    public GoodsService(GoodsDao jdbcGoods) {
        this.JDBC_GOODS = jdbcGoods;
    }

    public List<Goods> findAll() {
        return JDBC_GOODS.findAll();
    }

    public void add(Goods item) {
        item.setDate(LocalDateTime.now());
        JDBC_GOODS.add(item);
    }

    public void update(Goods item, int id) {
        item.setDate(LocalDateTime.now());
        JDBC_GOODS.update(item, id);
    }

    public void remove(int id) {
        JDBC_GOODS.remove(id);
    }
}
