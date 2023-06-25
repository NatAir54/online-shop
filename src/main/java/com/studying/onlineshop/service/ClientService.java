package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.jdbc.JdbcClient;
import com.studying.onlineshop.entity.Client;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private JdbcClient jdbcClient;

    public ClientService(JdbcClient client) {
        this.jdbcClient = client;
    }

    public List<Client> findAll() {
        List<Client> clientList = jdbcClient.findAll();
        System.out.println("Obtain clients: " + clientList.size());
        return clientList;
    }

    public void addNewClient(Client client){
        try {
            jdbcClient.addClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean clientSignIn(Client client) throws SQLException {
        List<Client> list = null;
        try {
            list = jdbcClient.findAll();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (Client c : list){
            if(c.getEmail().equals(client.getEmail())){
                return checkPassword(c, client);
            }
        }
        return false;
    }

    private boolean checkPassword(Client dbClient, Client logClient)  {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] message = messageDigest.digest((logClient.getPassword() + logClient.getSole()).getBytes());
        BigInteger number = new BigInteger(1, message);
        String hashtext = number.toString(16);

        System.out.println(hashtext);

        if(dbClient.getPassword().equals(hashtext)) {
            return true;
        }
        throw new IllegalArgumentException();
    }

    // ...
}
