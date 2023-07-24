package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.entity.Client;
import lombok.Getter;
import java.util.List;


@Getter
public class ClientService {
    private final ClientDao JDBC_CLIENT;


    public ClientService(ClientDao jdbcClient) {
        this.JDBC_CLIENT = jdbcClient;
    }


    public void add(Client client) {
        JDBC_CLIENT.add(client);
    }

    public List<Client> findAll() {
        return JDBC_CLIENT.findAll();
    }

    public Client findByEmail(String email) {
        return JDBC_CLIENT.findByEmail(email);
    }
}
