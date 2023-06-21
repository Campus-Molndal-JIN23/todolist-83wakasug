package org.campusmolndal;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteCRUD {
     private SQLite sqlite;
     private Connection conn;

    public SQLiteCRUD(SQLite sqlite){
        this.sqlite = sqlite;
    }


    public void addTODO(String query,String description,int assignTo){
            conn=sqlite.connection();

            try{
                PreparedStatement pstmt=conn.prepareStatement(query);
                pstmt.setString(1, description);
                pstmt.setInt(2,assignTo);
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }


    public void addUser(String query,String name,int age){
        conn=sqlite.connection();

        try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2,age);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Todo> showTodo(){
        Todo todo = null;
        ArrayList<Todo> topTen = new ArrayList<>();

        try{
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                player = new Player(
                        rst.getString("Name"),
                        rst.getInt("Points"));
                topTen.add(player);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return topTen;
    }



}
