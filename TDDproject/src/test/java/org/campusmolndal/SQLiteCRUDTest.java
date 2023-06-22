package org.campusmolndal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SQLiteCRUDTest {
    PreparedStatement mockPstm;
    Connection mockConn;
    Todo mockTodo;
    SQLite mockSqLite;
    DBCRUD sqLiteCrud;




    @BeforeEach
    void setUP() throws SQLException {
        Mockito.framework().clearInlineMocks();
        mockConn = Mockito.mock(Connection.class);
        mockPstm = Mockito.mock(PreparedStatement.class);
        mockTodo = Mockito.mock(Todo.class);
        mockSqLite = Mockito.mock(SQLite.class);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPstm);
        doNothing().when(mockPstm).setString(anyInt(),anyString());
        doNothing().when(mockPstm).setInt(anyInt(),anyInt());
        when(mockSqLite.connection()).thenReturn(mockConn);

        sqLiteCrud = new DBCRUD(mockConn);

    }

   /* @Test
    void add() throws SQLException {
        mockTodo.setId(1);
        mockTodo.setDone(5);
        mockTodo.setText("Test");
        sqLiteCrud.addData(mockTodo);
        verify(mockPstm).executeUpdate();
    }*/
}