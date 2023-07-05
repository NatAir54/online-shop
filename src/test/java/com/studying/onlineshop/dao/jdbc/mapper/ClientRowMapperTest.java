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
        when(resultSetMock.getInt("id")).thenReturn(5);
        when(resultSetMock.getString("email")).thenReturn("emailSpecified");
        when(resultSetMock.getString("password")).thenReturn("passwordSpecified");
        when(resultSetMock.getString("sole")).thenReturn("soleSpecified");

        Client actual = clientRowMapper.mapRow(resultSetMock);

        assertEquals(5, actual.getId());
        assertEquals("emailSpecified", actual.getEmail());
        assertEquals("passwordSpecified", actual.getPassword());
        assertEquals("soleSpecified", actual.getSole());
    }

}