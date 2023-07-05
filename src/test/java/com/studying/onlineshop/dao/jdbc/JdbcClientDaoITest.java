package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.dao.ClientDao;
import com.studying.onlineshop.entity.Client;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JdbcClientDaoITest {
    @Test
    public void testFindAllReturnCorrectData() {
        ClientDao jdbcClient = new JdbcClientDao();
        List<Client> clients = jdbcClient.findAll();
        assertFalse(clients.isEmpty());
        for(Client client : clients) {
            assertNotEquals(0, client.getId());
            assertNotNull(client.getEmail());
            assertNotNull(client.getPassword());
            assertNotNull(client.getSole());
        }
    }

}