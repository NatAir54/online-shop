package com.studying.onlineshop.service;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.entity.Client;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class ClientService {
    @Autowired
    private ClientDao JDBC_CLIENT;

    public ClientService() {
    }

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
