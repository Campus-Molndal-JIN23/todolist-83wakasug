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


    @BeforeEach
    void setUp() throws SQLException {
        String query = "test";
        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
        mockConn = Mockito.mock(Connection.class);
        mockRst = Mockito.mock(ResultSet.class);
        mockSqlite = Mockito.mock(SQLite.class);
        mockStm = Mockito.mock(Statement.class);
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(Mockito.anyString())).thenReturn(mockConn);
        when(mockSqlite.connection()).thenReturn(mockConn);
        when(mockConn.prepareStatement(query)).thenReturn(mockpstm);
        //when(mockStm.executeQuery(query)).thenReturn(any());
        when(mockStm.executeQuery(anyString())).thenReturn(mockRst);
        when(mockpstm.executeUpdate()).thenReturn(1);


    }

    void setupTodoResultSet() throws SQLException {
        when(mockRst.getInt("ID")).thenReturn(1);
        when(mockRst.getString("DESCRIPTION")).thenReturn("Home Work");
        when(mockRst.getInt("ASSIGNEDTO")).thenReturn(3);
        when(mockRst.getString("PROGRESS")).thenReturn("DONE");
    }
    void setupUserResultSet() throws SQLException {
        when(mockRst.getInt("ID")).thenReturn(1);
        when(mockRst.getString("NAME")).thenReturn("Hugo");
        when(mockRst.getInt("Age")).thenReturn(5);
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
    void verifyIfResultsetWorks() throws SQLException {
        mockRst = mockpstm.executeQuery();
        String value ="ID";
        String value2 ="ASSIGNEDTO ";
        String value3 ="PROGRESS";
        mockRst.getInt(value);
        mockRst.getString(value2);
        mockRst.getString(value3);

        verify(mockRst, times(1)).getString(value);
        verify(mockRst, times(1)).getInt(value2);
        verify(mockRst, times(1)).getInt(value3);
    }

    @Test
    void showAllTodo(){
        
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