package com.studying.onlineshop.dao.jdbc;

import com.studying.onlineshop.entity.Client;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JdbcClientITest {
    @Test
    public void testFindAllReturnCorrectData() {
        JdbcClient jdbcClientDao = new JdbcClient();
        List<Client> clients = jdbcClientDao.findAll();
        assertFalse(clients.isEmpty());
        for(Client client : clients) {
            assertNotEquals(0, client.getId());
            assertNotNull(client.getEmail());
            assertNotNull(client.getPassword());
            assertNotNull(client.getSole());
        }
    }



}