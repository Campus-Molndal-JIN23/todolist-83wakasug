package org.campusmolndal;

import java.sql.*;
import java.util.ArrayList;

public class DBCRUD {
     private SQLite sqlite;
     private Connection conn;

    public DBCRUD(SQLite sqlite){
        this.sqlite = sqlite;
    }


    public void addData(String query,String value1,int value2){
            conn=sqlite.connection();

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


    public ArrayList<Todo> showALLTodo(String query){
        Todo todo = null;
        ArrayList<Todo> list = new ArrayList<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                todo = new Todo(
                        rst.getInt("ID"),
                        rst.getString("DESCRIPTION"),
                        rst.getString("PROGRESS"),
                        rst.getString("ASSIGNEDTO"));
                list.add(todo);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    public Todo showONETodo(String query){
        Todo todo = null;

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                todo = new Todo(
                        rst.getInt("ID");
                        rst.getString("DESCRIPTION"),
                        rst.getString("PROGRESS"),
                        rst.getString("ASSIGNEDTO"));
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return todo;
    }


}
