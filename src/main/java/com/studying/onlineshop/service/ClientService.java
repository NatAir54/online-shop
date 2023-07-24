package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.entity.Client;
import lombok.Getter;
import java.util.List;


@Getter
public class ClientService {
    private final ClientDao jdbcClient;


    public ClientService(ClientDao jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public void add(Client client) {
        jdbcClient.add(client);
    }

    public List<Client> findAll() {
        return jdbcClient.findAll();
    }

    public Client findByEmail(String email) {
        return jdbcClient.findByEmail(email);
    }
}
