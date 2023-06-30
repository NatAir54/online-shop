package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.entity.Goods;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcGoodsITest {
    @Test
    public void testFindAllReturnCorrectData() {
        GoodsDao jdbcGoods = new JdbcGoodsDao();
        List<Goods> clients = jdbcGoods.findAll();
        assertFalse(clients.isEmpty());
        for(Goods item : clients) {
            assertNotEquals(0, item.getId());
            assertNotNull(item.getName());
            assertNotEquals(0, item.getPrice());
            assertNotNull(item.getDate());
        }
    }

}