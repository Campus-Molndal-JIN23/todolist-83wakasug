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
String url="jdbc:sqlite:test.db";
String dbName = "testDatabase";
SQLite sqLite;
ResultSet mockResultSet;


    @BeforeEach
void setUP() throws SQLException {
        Mockito.framework().clearInlineMocks();

        mockConn = Mockito.mock(Connection.class);
        mockStm = Mockito.mock(Statement.class);
        mockResultSet =Mockito.mock(ResultSet.class);
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(Mockito.anyString())).thenReturn(mockConn);

        when(mockConn.createStatement()).thenReturn(mockStm);
        when(mockStm.executeQuery(anyString())).thenReturn(mockResultSet);
        sqLite = new SQLite(url);

    }

@Test
void returnNulWhenConnIsnull() throws SQLException {

        Connection actual = sqLite.connection();
        assertEquals(mockConn, actual);
    }

  @Test
   void returnConnWhenConnectionIsClosed() throws SQLException {
      when(mockConn.isClosed()).thenReturn(false);
      Connection expected= mockConn;
      Connection actual = sqLite.connection();
       assertEquals(expected,actual);
   }

    @Test
    void sendConnWhenConnectionIsOpen() throws SQLException {
        when(mockConn.isClosed()).thenReturn(true);
        Connection expected = mockConn;
        Connection actual = sqLite.connection();
        assertEquals(expected,actual);
    }

    @Test
    void disconnectTestWhenConnectionIsClosed() throws SQLException {
        when(mockConn.isClosed()).thenReturn(true);
        when(mockResultSet.next()).thenReturn(false);
        boolean actual = sqLite.disConnect(this.mockConn);
        assertFalse(actual);
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