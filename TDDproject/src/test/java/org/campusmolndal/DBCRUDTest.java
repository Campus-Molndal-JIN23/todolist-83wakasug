package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
DBCRUD dbcrud;
Todo mockTodo;


    @BeforeEach
    void setUp() throws SQLException {
        String query = "test";
        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
        mockConn = Mockito.mock(Connection.class);
        mockRst = Mockito.mock(ResultSet.class);
        mockSqlite = Mockito.mock(SQLite.class);
        mockStm = Mockito.mock(Statement.class);
        mockTodo =Mockito.mock(Todo.class);
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(Mockito.anyString())).thenReturn(mockConn);
        when(mockSqlite.connection()).thenReturn(mockConn);
        when(mockConn.prepareStatement(query)).thenReturn(mockpstm);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockpstm);
        when(mockpstm.executeQuery()).thenReturn(mockRst);
        when(mockStm.executeQuery(anyString())).thenReturn(mockRst);
        when(mockStm.executeQuery(anyString())).thenReturn(mockRst);
        setupTodoResultSet();
        setupUserResultSet();
        when(mockRst.next()).thenReturn(true).thenReturn(false);
        when(mockpstm.executeUpdate()).thenReturn(1);


    }



    void setupTodoResultSet() throws SQLException {
        when(mockRst.getInt("ID")).thenReturn(1);
        when(mockRst.getString("DESCRIPTION")).thenReturn("Homework");
        when(mockRst.getInt("ASSIGNEDTO")).thenReturn(3);
        when(mockRst.getString("PROGRESS")).thenReturn("DONE");
    }
    void setupUserResultSet() throws SQLException {
        when(mockRst.getInt("ID")).thenReturn(1);
        when(mockRst.getString("NAME")).thenReturn("Hugo");
        when(mockRst.getInt("AGE")).thenReturn(5);
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
        String value = "ID";
        String value2 = "ASSIGNEDTO";
        String value3 = "PROGRESS";
        mockRst.getInt(value);
        mockRst.getInt(value2);
        mockRst.getString(value3);

        verify(mockRst, times(1)).getInt(value);
        verify(mockRst, times(1)).getInt(value2);
        verify(mockRst, times(1)).getString(value3);
    }


    @Test
    void ShowAllTodo() throws SQLException {
        Map<Todo, User> actual = new HashMap<>();
        Map<Todo, User> expected = new HashMap<>();
        User userTest = new User(1, "Hugo", 5);
        Todo todoTest = new Todo(1, "Homework", 3, "DONE");
        expected.put(todoTest, userTest);

        // Mock the behavior of mockStm.executeQuery()
        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showALLTodo("test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());
        //This returns false becuase of different hashmap IDs
        //assertEquals(expected, actual);
    }
    @Test
    void showTodo() throws SQLException {
         Map<Integer, Todo> actual = new HashMap<>();
        Map<Integer, Todo>expected =new HashMap<>();
        Todo todoTest = new Todo(1, "Homework", 3, "DONE");
        expected.put(1, todoTest);

        // Mock the behavior of mockStm.executeQuery()
        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showTodo("test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());
        //This returns false becuase of different hashmap IDs
        //assertEquals(expected, actual);

    }

    @Test
    void showONETodo() throws SQLException {
        Todo actual;
        Todo expected = new Todo(1, "Homework", 3, "DONE");
        System.out.println(mockRst);
        // Mock the behavior of mockStm.executeQuery()

        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showONETodo("Test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());
    }

    @Test
    void showSingleUser() throws SQLException {
        User actual;
        User expected =new User(1, "Hugo", 5);
        System.out.println(mockRst);
        // Mock the behavior of mockStm.executeQuery()

        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showSingleUser("Test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());
    }

    @Test
    void showUsers() throws SQLException {
        Map<Todo, User> actual = new HashMap<>();
        Map<Todo, User> expected = new HashMap<>();
        User userTest = new User(1, "Hugo", 5);
        Todo todoTest = new Todo(1, "Homework", 3, "DONE");
        expected.put(todoTest, userTest);

        // Mock the behavior of mockStm.executeQuery()
        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showALLTodo("test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());
    }

    @Test
    void showALLUser() throws SQLException {
      User userTest;
        Map<Integer, User> actual = new HashMap<>();
        Map<Integer, User>expected =new HashMap<>();
        userTest =new User(1, "Hugo", 5);
        expected.put(1, userTest);

        // Mock the behavior of mockStm.executeQuery()
        when(mockpstm.executeQuery()).thenReturn(mockRst);

        // Create an instance of DBCRUD
        DBCRUD sq = new DBCRUD(mockSqlite);

        // Assign the mockRst to the rst variable

        actual = sq.showUsers("test");

        //to string so it compares only values, and not hashmap ids.
        assertEquals(actual.toString(),expected.toString());

    }



    @Test
    void deleteData() {
    }
}