package org.campusmolndal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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



}
