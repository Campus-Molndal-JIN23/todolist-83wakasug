package org.campusmolndal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBCRUD {
    private SQLite sqlite;
    private Connection conn;

    public DBCRUD(SQLite sqlite){
        this.sqlite = sqlite;
    }


    public PreparedStatement preparedStatement(String query) throws SQLException {
        PreparedStatement pstmt=conn.prepareStatement(query);
        return pstmt;
    }
    private ResultSet getQuery(String query) throws SQLException {
        PreparedStatement pstmt = preparedStatement(query);
        return pstmt.executeQuery();
    }

    private Todo createTodoObject(ResultSet rst) throws SQLException{
       Todo todo;
        todo = new Todo(
                rst.getInt("ID"),
                rst.getString("DESCRIPTION"),
                rst.getInt("ASSIGNEDTO"),
                rst.getString("PROGRESS"));
        return todo;
    }
    private User createUserObject(ResultSet rst) throws SQLException{
        User user;
        user = new User(
                rst.getInt("ID"),
                rst.getString("NAME"),
                rst.getInt("AGE")
        );
        return user;
    }

    public void addData(String query,String value1,int value2){
        conn = sqlite.connection();

        try{
            addDataExecuteQuery (query,value1,value2);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        sqlite.disConnect(conn);
    }

    private void addDataExecuteQuery (String query,String value1,int value2) throws SQLException {

        PreparedStatement pstmt = preparedStatement(query);
        pstmt.setString(1, value1);
        pstmt.setInt(2,value2);
        pstmt.executeUpdate();
    }

    /**
     * Retrieves all Todo objects with their corresponding User objects from the database.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return A Map containing Todo objects as keys and User objects as values.
     */

    public Map<Todo,User> showALLTodo(String query){
        conn = sqlite.connection();

        try{
            ResultSet rst = getQuery(query);
           return  CreateTodoUserMap(rst);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);

        return new HashMap<>();

    }



    private Map<Todo,User> CreateTodoUserMap(ResultSet rst) throws SQLException {
        Map<Todo, User> allTodo = new HashMap<>();

        while(rst.next()){

            allTodo.put(createTodoObject(rst),createUserObject(rst));
        }
        return allTodo;
    }


    public Map<Integer,Todo> showTodo(String query){
        conn = sqlite.connection();

        Map<Integer, Todo> allTodo = new HashMap<>();
        try{
            ResultSet rst = getQuery(query);
            return  CreateIntTODOMap(rst);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return  new HashMap<>();
    }

    private Map<Integer,Todo> CreateIntTODOMap(ResultSet rst) throws SQLException {
        Map<Integer, Todo> allTodo = new HashMap<>();

        int i = 1;
        while(rst.next()){
            allTodo.put(i,createTodoObject(rst));
            i++;
        }
        return allTodo;
    }

    public Todo showONETodo(String query){
        this.conn = sqlite.connection();
        Todo todo = null;

        try{
            ResultSet rst = getQuery(query);
            return  createTodo(rst);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return todo;
    }

    private Todo createTodo(ResultSet rst) throws SQLException {
        Todo todo = null;

        while(rst.next()){
            todo = createTodoObject(rst);
        }
        return todo;
    }


    public User showSingleUser(String query){
        this.conn = sqlite.connection();
        User user = null;
        try{
            ResultSet rst = getQuery(query);
            return  createUser(rst);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return user;
    }

    private User createUser(ResultSet rst) throws SQLException {
            User user = null;
            while(rst.next()){
                user = createUserObject(rst);
            }
        return user;
    }


    public Map<Integer,User> showUsers(String query){
        this.conn = sqlite.connection();

        try{
            ResultSet rst = getQuery(query);
            return  createIntUserMap(rst);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return new HashMap<>();
    }

    private Map<Integer,User> createIntUserMap(ResultSet rst) throws SQLException {
        User user = null;
        Todo todo = null;
        Map<Integer,User>allUsers = new HashMap<Integer,User>();

        while(rst.next()){
            if(!allUsers.containsKey(rst.getInt("ID"))){

                user = user = createUserObject(rst);

                allUsers.put(user.getId(), user);
                user.setTodos(new ArrayList<>());
            }
            todo =  createTodoObject(rst);

            allUsers.get(rst.getInt("ID")).addTodo(todo);
        }
        return allUsers;
    }



    public Map<Integer,User> showALLUser(String query){
        conn=sqlite.connection();


        try{
            ResultSet rst = getQuery(query);
            return  createIndexUserMap(rst);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return  new HashMap<>();
    }

    private Map<Integer,User> createIndexUserMap(ResultSet rst) throws SQLException {
        User user;
        Map<Integer, User> usersList = new HashMap<>();

        int i = 1;
        while(rst.next()){
            user = createUserObject(rst);
            usersList.put(i,user);
            i++;
        }
        return usersList;
    }



    public void updateDataInt(String query,int value) {
        conn=sqlite.connection();
        try {
            preparedStatement1IntExecuteQuery(query,value);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }

    private void preparedStatement1IntExecuteQuery (String query,int value1) throws SQLException {

        PreparedStatement pstmt = preparedStatement(query);
        pstmt.setInt(1, value1);
        pstmt.executeUpdate();
    }

    public void updateDataString(String query,String value) {
        conn=sqlite.connection();
        try {
            preparedStatement1StringExecuteQuery(query,value);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }
    private void preparedStatement1StringExecuteQuery (String query, String value1) throws SQLException {

        PreparedStatement pstmt = preparedStatement(query);
        pstmt.setString(1, value1);
        pstmt.executeUpdate();
    }

    public void deleteData(String query,int id)  {
        conn=sqlite.connection();
        try {
            preparedStatement1IntExecuteQuery(query,id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }

}