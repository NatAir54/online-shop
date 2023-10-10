package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.dao.jdbc.mapper.ClientRowMapper;
import com.studying.onlineshop.entity.Client;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcClientDao implements ClientDao {
    private static final ClientRowMapper CLIENT_ROW_MAPPER = new ClientRowMapper();
    private static final String SQL_CREATE_TABLE = """
            CREATE TABLE clients (id SERIAL PRIMARY KEY, email varchar(50) NOT NULL, password varchar(50) NOT NULL, sole varchar(150) NOT NULL);""";
    private static final String SQL_INSERT_INTO = "INSERT INTO clients (email, password, sole) VALUES (?, ?, ?);";
    private static final String SQL_FIND_ALL = "SELECT id, email, password, sole FROM clients;";



    public List<Client> findAll() {
        if (checkTableNotExist()) {
            createTable();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                Client client = CLIENT_ROW_MAPPER.mapRow(resultSet);
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while find all clients", e);
        }
    }

    public void add(Client client) {
        if (checkTableNotExist()) {
            createTable();
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO)) {
            preparedStatement.setString(1, client.getEmail());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getSole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while add client", e);
        }
    }

    public Client findByEmail(String email) {
        List<Client> list = findAll();
        for (Client dbClient : list) {
            if (Objects.equals(dbClient.getEmail(), email)) {
                return dbClient;
            }
        }
        return null;
    }

    public void createTable() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkTableNotExist() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "clients", null);
            if (resultSet.next()) {
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
