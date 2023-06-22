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


    public void addData(String query,String value1,int value2){

            try{
                PreparedStatement pstmt=conn.prepareStatement(query);
                pstmt.setString(1, value1);
                pstmt.setInt(2,value2);
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }


    public Map<Todo,User> showALLTodo(String query){
        conn = sqlite.connection();
        Todo todo = null;
        User user = null;
        Map<Todo, User> allTodo = new HashMap<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

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

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return  allTodo;
    }

    public Todo showONETodo(String query){
        conn = sqlite.connection();
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

        return todo;
    }

    public Map<Integer,User> showUsers(String query){
        conn = sqlite.connection();
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
                }
                todo = new Todo(
                        rst.getInt("TODOID"),
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
        return allUsers;
    }

    public ArrayList<User> showALLUser(String query){
        conn = sqlite.connection();
             User user;
             ArrayList <User> usersList = null;
        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                user = new User(
                        rst.getInt("ID"),
                        rst.getString("NAME"),
                        rst.getInt("AGE")
                );

                usersList.add(user);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return  usersList;
    }

    public void updateDataInt(String query,int value) throws SQLException {
        conn = sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void updateDataString(String query,String value) throws SQLException {
        conn = sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void deleteData(String query,int id) throws SQLException {
        conn = sqlite.connection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }



}
