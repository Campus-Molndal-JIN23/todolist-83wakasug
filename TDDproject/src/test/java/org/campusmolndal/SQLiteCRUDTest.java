package org.campusmolndal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SQLiteCRUDTest {
 PreparedStatement mockPstm;
 Connection mockConn;
 Todo mockTodo;

    @BeforeEach
    void setUp(){
        mockPstm = Mockito.mock(PreparedStatement.class);
        mockConn =Mockito.mock(Connection.class);
        mockTodo = Mockito.mock(Todo.class);

        when(mockTodo.getText()).thenReturn("Cleaning");
        when(mockTodo.getDone()).thenReturn(1);


    }

    @Test
    void add() throws SQLException {


        String sql = "INSERT INTO TODO (NAME, POINTS) VALUES (?, ?)";

        verify(mockConn.prepareStatement(sql));
        verify(mockPstm).setString(1, mockTodo.getText());
        verify(mockPstm).setInt(2, mockTodo.getDone());
        verify(mockPstm).executeUpdate();

    }
}