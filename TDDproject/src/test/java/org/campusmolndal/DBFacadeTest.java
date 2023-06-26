package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DBFacadeTest {
    PreparedStatement mockpstm;
    Connection mockConn;
    ResultSet mockRst;
    SQLite mockSqlite;
    Statement mockStm;
    DBCRUD mockDBCRUD;
    DBFacade dbFacade;
    DBFacade mockDbFacade;
    Todo mockTDOO;
    Map <Integer, Todo> mockMap;
    Map <Integer, User> mockMapIntUser;
    Map <Todo, User> mockMaptodoUser;


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    void setUp() throws SQLException {
        String query = "test";

        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errContent));

        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
        mockConn = Mockito.mock(Connection.class);
        mockRst = Mockito.mock(ResultSet.class);
        mockSqlite = Mockito.mock(SQLite.class);
        mockDBCRUD =Mockito.mock(DBCRUD.class);
        mockTDOO =Mockito.mock(Todo.class);

        mockStatic(DriverManager.class);
        mockStm = Mockito.mock(Statement.class);
        mockMap = new HashMap<>();
        mockMapIntUser = new HashMap<> ();
        mockMaptodoUser = new HashMap<>();
        when(DriverManager.getConnection(Mockito.anyString())).thenReturn(mockConn);
        when(mockSqlite.connection()).thenReturn(mockConn);
        when(mockConn.prepareStatement(query)).thenReturn(mockpstm);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockpstm);
        when(mockConn.createStatement()).thenReturn(mockStm);
        when(mockpstm.executeQuery()).thenReturn(mockRst);
        when(mockStm.executeQuery(anyString())).thenReturn(mockRst);
        when(mockStm.executeQuery(anyString())).thenReturn(mockRst);
        setupTodoResultSet();
        setupUserResultSet();
        when(mockRst.next()).thenReturn(true).thenReturn(false);
        when(mockpstm.executeUpdate()).thenReturn(1);
        dbFacade = new DBFacade("test");
        dbFacade.dbCRUD = mockDBCRUD;

        mockDbFacade =Mockito.mock(DBFacade.class);
        mockDbFacade.dbCRUD = mockDBCRUD;

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
    void connectDB() {
        Connection actual =  dbFacade.connectDB();
        Connection expected = mockConn;
        assertEquals(actual,expected);
    }

    @Test
    void showALLTODOList() {
        // Stub the mockDbCrud method
        when(mockDBCRUD.showALLTodo(anyString())).thenReturn(mockMaptodoUser);
        Map<Todo, User> allTodoList = dbFacade.showALLTODOList();
        assertSame(mockMaptodoUser,allTodoList);
    }

    @Test
    void showALLTODO() {
        doCallRealMethod().when(mockDbFacade).showALLTODO(any());
        mockDbFacade.showALLTODO(mockMaptodoUser);
        System.out.println(outputStream.toString());
        //verify(mockDbFacade).showResult(any(),any());
    }

    @Test
    void showALLTODOEmpty() {

        // Create a mock todo-user map
        Map<Todo, User> emptyMockTodoUser = new HashMap<>();
        dbFacade.showALLTODO(emptyMockTodoUser);
        assertEquals("There are no todos",outputStream.toString().trim());
    }

    @Test
    void showOnlyDescription() {
        when(mockDBCRUD.showTodo(anyString())).thenReturn(mockMap);
        Map<Integer, Todo> expected = dbFacade.showOnlyDescription();
        Map<Integer, Todo> actual =dbFacade.showOnlyDescription();

        assertEquals(expected.toString().trim(),actual.toString().trim());
    }

    @Test
    void showONETODO() {

        // Create a mock todo-user map
        Map<Todo, User> mockTodoUserMap = new HashMap<>();
        User user = new User(1, "Wakana",39);
        Todo todo = new Todo(1, "Homework", 3, "DONE");
        mockTodoUserMap.put(todo, user);


        // Stub the mockDbCrud method
        when(mockDBCRUD.showALLTodo(anyString())).thenReturn(mockTodoUserMap);

        // Call the method
        dbFacade.showONETODO(1);

        assertTrue(outputStream.toString().contains("Wakana"));
    }

    @Test
    void showSingleUser() {

        // Create a mock user map
        Map<Integer, User> mockUserMap = new HashMap<>();
        User user = new User(1, "Wakana",39);
        mockUserMap.put(1, user);

        // Stub the mockDbCrud methods
        when(mockDBCRUD.showSingleUser(anyString())).thenReturn(user);
        when(mockDBCRUD.showUsers(anyString())).thenReturn(mockUserMap);

        // Call the method
        dbFacade.showSingleUser(1);

        assertTrue(outputStream.toString().contains("Wakana"));
        verify(mockDBCRUD, times(1)).showSingleUser(DBQuery.showSingleUser(1));
        verify(mockDBCRUD, times(1)).showUsers(DBQuery.showSingleUser(1));
    }

    @Test
    void showUsersList() {
        when(mockDBCRUD.showALLUser(anyString())).thenReturn(mockMapIntUser);
        Map<Integer, Todo> expected = dbFacade.showOnlyDescription();
        Map<Integer, User> actual =dbFacade.showUsersList();

        assertEquals(expected.toString().trim(),actual.toString().trim());
    }

    @Test
    void testUpdateString() {
        String table = "table";
        String column = "column";
        int id = 1;
        String description = "new description";

        dbFacade.updateString(table, column, id, description);

        verify(mockDBCRUD).updateDataString(DBQuery.updateTODOTable(table, column, id).trim(), description.trim());
    }

    @Test
    void testUpdateInt() {
        String table = "table";
        String column = "column";
        int id = 1;
        int statusNo = 2;

        dbFacade.updateInt(table, column, id, statusNo);

        verify(mockDBCRUD).updateDataInt(DBQuery.updateTODOTable(table, column, id).trim(), statusNo);
    }

    @Test
    void testAddTODO() {
        String value1 = "value1";
        int value2 = 2;

        dbFacade.addTODO(value1, value2);

        verify(mockDBCRUD).addData(DBQuery.addDataToTODO(), value1, value2);
    }

    @Test
    void testAddUser() {
        String name = "Toma";
        int age = 1;

        dbFacade.addUser(name, age);

        verify(mockDBCRUD).addData(DBQuery.addDataToUser(), name, age);
    }

    @Test
    void testDeleteData() {
        String table = "table";
        int id = 1;

        dbFacade.deleteData(table, id);

        verify(mockDBCRUD).deleteData(DBQuery.deleteData(table), id);
    }

    @Test
    void showAllUsers() {
        // Create a mock user map
        Map<Integer, User> mockUserMap = new HashMap<>();
        User user1 = new User(1, "Toma",1);
        User user2 = new User(2, "Hugo",5);
        mockUserMap.put(1, user1);
        mockUserMap.put(2, user2);

        dbFacade.showAllUsers(mockUserMap);

        assertTrue(outputStream.toString().contains("Toma"));
        assertTrue(outputStream.toString().contains("Hugo"));
    }



    @Test
    void showResult() {
    }

    @Test
  /*  void showSingleResult() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a user and add some todos
        User user = new User(1, "Test", 100);
        ArrayList<Todo> todoList = new ArrayList<>();
        todoList.add(new Todo(1, "Sleep", 50, "TODO"));
        todoList.add(new Todo(2, "Work", 100, "DONE"));
        user.setTodos(todoList);

        // Create an instance of YourClassName

        // Call the method
        dbFacade.showSingleResult(user);

        // Assert the expected output
        String expectedOutput = "Progress: TODO\r\n" +
                "Todo :Sleep\r\n" +
                "Progress: DONE\r\n" +
                "Todo :Work\r\n" +
                "-----------------------------------------\r\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }


     @Test
    void showSingleResultNull() {
        // Create a User object with null assignment
        User user = new User(1, "Wasabi",13);


        // Call the method and capture the output
        dbFacade.showSingleResultNull(user);


        assertTrue(outputStream.toString().trim().contains("Wasabi"));
    }*/
}