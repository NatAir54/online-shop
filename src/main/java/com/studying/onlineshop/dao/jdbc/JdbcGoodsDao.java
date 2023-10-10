package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.dao.GoodsDao;
import com.studying.onlineshop.dao.jdbc.mapper.GoodsRowMapper;
import com.studying.onlineshop.entity.Goods;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGoodsDao implements GoodsDao {
    private static final GoodsRowMapper GOODS_ROW_MAPPER = new GoodsRowMapper();
    private static final String SQL_CREATE_TABLE = """
            CREATE TABLE goods (id SERIAL PRIMARY KEY, name varchar(50) NOT NULL, price int NOT NULL, dateTimeAdded DATE);""";
    private static final String SQL_INSERT_INTO = "INSERT INTO goods (name, price, dateTimeAdded) VALUES (?, ?, ?);";
    private static final String SQL_FIND_ALL = "SELECT id, name, price, dateTimeAdded FROM goods";
    private static final String SQL_UPDATE = "UPDATE goods SET name = ?, price = ?, dateTimeAdded = ? WHERE id = ?;";
    private static final String SQL_REMOVE = "DELETE FROM goods WHERE id = ?;";


    public void add(Goods item) {
        if(checkTableNotExists()){
            createTable();
        }
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
        if(checkTableNotExists()){
            createTable();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery())
        {

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
        if(checkTableNotExists()){
            createTable();
        }
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
        if(checkTableNotExists()){
            createTable();
        }
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while remove goods", e);
        }
    }

    private void createTable() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  boolean checkTableNotExists() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "goods", null);
            if(resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "postgres", "postgrsql098");
    }
}
