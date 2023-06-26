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

    private ResultSet getQuery(String query) throws SQLException {
        PreparedStatement pstmt = preparedStatement(query);
        return pstmt.executeQuery();
    }

    private Map<Todo,User> CreateTodoUserMap(ResultSet rst) throws SQLException {
        Map<Todo, User> allTodo = new HashMap<>();
        Todo todo = null;
        User user = null;

        while(rst.next()){
            todo = new Todo(
                    rst.getInt("ID"),
                    rst.getString("DESCRIPTION"),
                    rst.getInt("ASSIGNEDTO"),
                    rst.getString("PROGRESS"));


            user = new User(
                    rst.getInt("ID"),
                    rst.getString("NAME"),
                    rst.getInt("AGE")
            );
            allTodo.put(todo,user);
        }
        return allTodo;
    }

    private
    /**
     * Retrieves Todo objects from the database and stores them in a map with integer keys.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return A Map with integer keys and Todo objects as values.
     */

    public Map<Integer,Todo> showTodo(String query){
        conn = sqlite.connection();
        Todo todo = null;
        Map<Integer, Todo> allTodo = new HashMap<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            int i = 1;
            while(rst.next()){
                todo = new Todo(
                        rst.getInt("ID"),
                        rst.getString("DESCRIPTION"),
                        rst.getInt("ASSIGNEDTO"),
                        rst.getString("PROGRESS"));

                allTodo.put(i,todo);
                i++;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return  allTodo;
    }
    /**
     * Retrieves a single Todo object from the database based on the given query.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return The Todo object retrieved from the database, or null if no result is found.
     */
    public Todo showONETodo(String query){
        this.conn = sqlite.connection();
        Todo todo = null;

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                todo = new Todo(
                        rst.getInt("ID"),
                        rst.getString("DESCRIPTION"),
                        rst.getInt("ASSIGNEDTO"),
                        rst.getString("PROGRESS")
                );
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return todo;
    }

    /**
     * Retrieves a single User object from the database based on the given query.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return The User object retrieved from the database, or null if no result is found.
     */

    public User showSingleUser(String query){
        this.conn = sqlite.connection();
        User user = null;

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                user = new User(
                        rst.getInt("ID"),
                        rst.getString("NAME"),
                        rst.getInt("AGE")
                );
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        sqlite.disConnect(conn);
        return user;
    }


    /**
     * Retrieves User objects from the database and stores them in a map with integer keys.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return A Map with integer keys and User objects as values.
     */
    public Map<Integer,User> showUsers(String query){
        this.conn = sqlite.connection();
        User user = null;
        Todo todo = null;
        Map<Integer,User>allUsers = new HashMap<Integer,User>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();


            while(rst.next()){
                if(!allUsers.containsKey(rst.getInt("ID"))){

                    user = new User(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getInt("AGE")
                    );
                    allUsers.put(user.getId(), user);
                    user.setTodos(new ArrayList<>());
                }
                todo = new Todo(
                        rst.getInt("ID"),
                        rst.getString("DESCRIPTION"),
                        rst.getInt("AssignedTo"),
                        rst.getString("Status")
                );

                allUsers.get(rst.getInt("ID")).addTodo(todo);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return allUsers;
    }

    /**
     * Retrieves all User objects from the database and stores them in a map with integer keys.
     *
     * @param query The SQL query to retrieve the data from the database.
     * @return A Map with integer keys and User objects as values.
     */
    public Map<Integer,User> showALLUser(String query){
        conn=sqlite.connection();
        User user;
        Map<Integer, User> usersList = new HashMap<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();
            int i = 1;
            while(rst.next()){
                user = new User(
                        rst.getInt("ID"),
                        rst.getString("NAME"),
                        rst.getInt("AGE")
                );
                usersList.put(i,user);
                i++;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
        return  usersList;
    }

    /**
     * Updates an integer value in the database based on the given query.
     *
     * @param query The SQL query to update the data in the database.
     * @param value The new integer value to be updated.
     */
    public void updateDataInt(String query,int value) {
        conn=sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }

    /**
     * Updates a string value in the database based on the given query.
     *
     * @param query The SQL query to update the data in the database.
     * @param value The new string value to be updated.
     */
    public void updateDataString(String query,String value) {
        conn=sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }

    /**
     * Deletes data from the database based on the given query and ID.
     *
     * @param query The SQL query to delete the data from the database.
     * @param id The ID of the data to be deleted.
     */
    public void deleteData(String query,int id)  {
        conn=sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sqlite.disConnect(conn);
    }

}