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

    public void add(Todo todo){
            conn=sqlite.connection();

            String sql = "INSERT INTO TODO (NAME, POINTS) VALUES (?, ?)";

            try{
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, todo.getText());
                pstmt.setInt(2,todo.getDone());
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }



}
