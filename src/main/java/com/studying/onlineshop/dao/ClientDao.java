package com.studying.onlineshop.dao;

import com.studying.onlineshop.entity.Client;

import java.util.List;

public interface ClientDao {
    List<Client> findAll();

    void add(Client client);

    Client findClient(String email);
}
