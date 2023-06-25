package com.studying.onlineshop.dao.jdbc.mapper;

import com.studying.onlineshop.entity.Goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GoodsRowMapper {
    public Goods mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        LocalDateTime dateTimeAdded = resultSet.getTimestamp("dateTimeAdded").toLocalDateTime();

        Goods good = Goods.builder().
                id(id).
                name(name).
                price(price).
                date(dateTimeAdded).
                build();

        return good;
    }
}
