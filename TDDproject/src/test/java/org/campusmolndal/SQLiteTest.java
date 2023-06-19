package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SQLiteTest {

Connection mockConn = null;
String url="jdbc:sqlite:" + this.dbName + ".db";
String dbName = "testDatabase";
SQLite sqlite;


    @BeforeEach
void setUP() throws SQLException {
     mockConn = Mockito.mock(Connection.class);
        mockStatic(DriverManager.class);

        when(DriverManager.getConnection(url)).thenReturn(mockConn);
        when(DriverManager.getConnection(null)).thenReturn(null);

}

@Test
void connectionTest() throws SQLException {

        Connection expected = null;
        assertEquals(DriverManager.getConnection(null),null);
    }


}