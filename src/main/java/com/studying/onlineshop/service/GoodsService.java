package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.jdbc.JdbcGoods;
import com.studying.onlineshop.entity.Goods;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GoodsService {
    private JdbcGoods jdbcGoods;

    public GoodsService(JdbcGoods jdbcGoods) {
        this.jdbcGoods = jdbcGoods;
    }

    public List<Goods> findAll() {
        return jdbcGoods.findAll();
    }

    public void add(Goods item) throws SQLException {
        item.setDate(LocalDateTime.now());
        jdbcGoods.add(item);
    }

    public void update(Goods item, int id) throws SQLException {
        item.setDate(LocalDateTime.now());
        jdbcGoods.update(item, id);
    }

    public void remove(int id) throws SQLException {
        jdbcGoods.remove(id);
    }
}
