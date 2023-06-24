package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DBCRUDTest {
PreparedStatement mockpstm;
Connection mockConn;
ResultSet mockRst;
SQLite mockSqlite;
Statement mockStm;
ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        String query = "test";
        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
        mockConn = Mockito.mock(Connection.class);
        mockRst = Mockito.mock(ResultSet.class);
        mockSqlite = Mockito.mock(SQLite.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        mockStm = Mockito.mock(Statement.class);
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(Mockito.anyString())).thenReturn(mockConn);
        when(mockSqlite.connection()).thenReturn(mockConn);
        when(mockConn.prepareStatement(query)).thenReturn(mockpstm);
        //when(mockStm.executeQuery(query)).thenReturn(any());
        when(mockStm.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockpstm.executeUpdate()).thenReturn(1);

    }

    @Test
    void checkIfmockpstmAndResultSetRuns() throws SQLException {
        String query = "test";
        int expected = 1;
        mockpstm = mockConn.prepareStatement(query);
        mockRst = mockpstm.executeQuery();
        int actual =mockpstm.executeUpdate();
        verify(mockConn,times(1)).prepareStatement(query);
        verify(mockpstm,times(1)).executeQuery();

        assertEquals(expected,actual);
    }
    void setUpHashMap(){


    }


    @Test
    void addData() throws SQLException {
       String value = "DESCRIPTION";
       int value2 = 1;
        mockpstm.setString(1, value);
        mockpstm.setInt(2, value2);


        verify(mockpstm, times(1)).setString(1, value);
        verify(mockpstm, times(1)).setInt(2, value2);
    }

    @Test
    void showALLTodo() {
    }

    @Test
    void showTodo() {
    }

    @Test
    void showONETodo() {
    }

    @Test
    void showSingleUser() {
    }

    @Test
    void showUsers() {
    }

    @Test
    void showALLUser() {
    }

    @Test
    void updateDataInt() {
    }

    @Test
    void updateDataString() {
    }

    @Test
    void deleteData() {
    }
}