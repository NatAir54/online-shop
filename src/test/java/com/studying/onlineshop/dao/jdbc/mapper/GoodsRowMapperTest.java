package com.studying.onlineshop.dao.jdbc.mapper;

import com.studying.onlineshop.entity.Goods;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GoodsRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        GoodsRowMapper goodsRowMapper = new GoodsRowMapper();
        LocalDateTime now = LocalDateTime.of(2023, 6, 22, 21, 7, 7);

        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt("id")).thenReturn(25);
        when(resultSetMock.getString("name")).thenReturn("name");
        when(resultSetMock.getInt("price")).thenReturn(500);
        when(resultSetMock.getTimestamp("dateTimeAdded")).thenReturn(Timestamp.valueOf(now));

        Goods actual = goodsRowMapper.mapRow(resultSetMock);

        assertEquals(25, actual.getId());
        assertEquals("name", actual.getName());
        assertEquals(500, actual.getPrice());
        assertEquals(now, actual.getDate());
    }

}