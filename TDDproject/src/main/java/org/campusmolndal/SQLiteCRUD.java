package org.campusmolndal;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteCRUD {
     private SQLite sqlite;
     private Connection conn;

    public SQLiteCRUD(SQLite sqlite){
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


    public ArrayList<Todo> showTodo(String query){
        Todo todo = null;
        ArrayList<Todo> list = new ArrayList<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                todo = new Todo(
                        rst.getString("DESCRIPTION"),
                        rst.getString("STATUS"),
                        rst.getString("ASSIGNEDTO"));
                list.add(todo);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return topTen;
    }



}
