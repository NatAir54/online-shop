package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.dao.jdbc.mapper.GoodsRowMapper;
import com.studying.onlineshop.entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGoodsDao implements GoodsDao {
    private static final GoodsRowMapper GOODS_ROW_MAPPER = new GoodsRowMapper();
    private static final String SQL_CREATE_TABLE = "CREATE TABLE goods (id SERIAL PRIMARY KEY, name varchar(50) NOT NULL, price int NOT NULL, dateTimeAdded DATE);";
    private static final String SQL_INSERT_INTO = "INSERT INTO goods (name, price, dateTimeAdded) VALUES (?, ?, ?);";
    private static final String SQL_FIND_ALL = "SELECT id, name, price, dateTimeAdded FROM goods";
    private static final String SQL_UPDATE = "UPDATE goods SET name = ?, price = ?, dateTimeAdded = ? WHERE id = ?;";
    private static final String SQL_REMOVE = "DELETE FROM goods WHERE id = ?;";

    public void createGoodsTable() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Goods item) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while add goods ", e);
        }
    }

    public List<Goods> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Goods> goods = new ArrayList<>();
            while (resultSet.next()) {
                Goods good = GOODS_ROW_MAPPER.mapRow(resultSet);
                goods.add(good);
            }
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while find all goods", e);
        }
    }

    public void update(Goods item, int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getDate()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while update goods", e);
        }
    }

    public void remove(int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while remove goods", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "postgres", "postgrsql098");
    }
}
