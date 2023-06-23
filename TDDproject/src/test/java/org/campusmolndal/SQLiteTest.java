package org.campusmolndal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SQLiteTest {

Connection mockConn ;
Statement mockStm;
String url="jdbc:sqlite:" + this.dbName + ".db";
String dbName = "testDatabase";
SQLite sqLite;


    @BeforeEach
void setUP() throws SQLException {
        Mockito.framework().clearInlineMocks();

        mockConn = Mockito.mock(Connection.class);
        mockStm = Mockito.mock(Statement.class);
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(url)).thenReturn(mockConn);
        when(DriverManager.getConnection(null)).thenReturn(null);
        when(mockConn.createStatement()).thenReturn(mockStm);
        sqLite = new SQLite(url);

    }

@Test
void connectionTestWhenURLISNULL() throws SQLException {

        assertEquals(DriverManager.getConnection(null), null);

    }

  @Test
   void connectDatabaseThenReturnMessageConnected() throws SQLException {
      when(mockConn.isClosed()).thenReturn(false);
      Connection expected= mockConn;
      Connection actual = sqLite.connection();
       assertEquals(expected,actual);
   }

    @Test
    void connectDatabaseThenReturnNull() throws SQLException {
        when(mockConn.isClosed()).thenReturn(true);
        Connection actual = sqLite.connection();
        assertNull(actual);
    }

    @Test
    void disconnectTestWhenConnectionIsClosed() throws SQLException {
        when(mockConn.isClosed()).thenReturn(true);
          boolean actual = sqLite.disConnect(this.mockConn);

        assertTrue(actual);
    }

    @Test
    void disconnectTestWhenConnectionIsOpen() throws SQLException {

        when(mockConn.isClosed()).thenReturn(false);
        boolean actual = sqLite.disConnect(this.mockConn);
        assertFalse(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"CREATE TABLE IF NOT EXISTS TODO (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(50) NOT NULL, TEXT VARCHAR(200), PROGRESS INTEGER, AssignedTo INTEGER, FOREIGN KEY (PROGRESS) REFERENCES Progress(ProgressID))", "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
            "ProgressID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "STATUS VARCHAR(50) NOT NULL"+
            ")"
            } )
    void createTableSuccessful(String query) throws SQLException {

        when(mockStm.execute(query)).thenReturn(true);
        boolean actual = sqLite.createTable(query);
        boolean expected = true;

        assertEquals(expected,actual);
    }

    @Test
    void createTableNotSuccessful() throws SQLException {
        String query = "Create Table 123";
        String errorMessage = "[SQLITE_ERROR] SQL error or missing database (near \"123\": syntax error)";
        SQLException exception = new SQLException(errorMessage);
        boolean expected = false;
        when(mockStm.execute(query)).thenThrow(exception);
        boolean actual = sqLite.createTable(query);

        assertEquals(expected, actual);
    }

}