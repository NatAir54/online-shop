package com.studying.onlineshop.dao.jdbc.mapper;

import com.studying.onlineshop.entity.Client;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper {

    public Client mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sole = resultSet.getString("sole");

        Client client = Client.builder().
                id(id).
                email(email).
                password(password).
                sole(sole).
                build();

        return client;
    }

}
