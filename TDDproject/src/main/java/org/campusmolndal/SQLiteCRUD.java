package org.campusmolndal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteCRUD {
     private SQLite sqlite;
     private Connection conn;

    public SQLiteCRUD(SQLite sqlite){
        this.sqlite = sqlite;
    }

    public void add(Object object,String sql){
            conn=sqlite.connection();

             sql = "INSERT INTO TODO (TODO, PROGRESS) VALUES (?, ?)";

            try{
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, object.getText());
                pstmt.setInt(2,todo.getDone());
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }



}
