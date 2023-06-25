package com.studying.onlineshop.dao.jdbc.mapper;

import com.studying.onlineshop.entity.Client;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        ClientRowMapper clientRowMapper = new ClientRowMapper();
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("id")).thenReturn(25);
        when(resultSetMock.getString("email")).thenReturn("email");
        when(resultSetMock.getString("password")).thenReturn("pass");
        when(resultSetMock.getString("sole")).thenReturn("sole");

        Client actual = clientRowMapper.mapRow(resultSetMock);

        assertEquals(25, actual.getId());
        assertEquals("email", actual.getEmail());
        assertEquals("pass", actual.getPassword());
        assertEquals("sole", actual.getSole());
    }

}
