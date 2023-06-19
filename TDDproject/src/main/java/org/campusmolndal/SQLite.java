package org.campusmolndal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    private String dbName = null;
    Connection connection;


    Connection conn;
    String url="jdbc:sqlite:" + dbName+ ".db";

    // The constructor for SQLite class calls connect() and createTable() methods.
    public SQLite(String dbName) throws SQLException {
        this.dbName = dbName;
        connect();
        createTable();
    }
    /**
     *
     * code to connect to a database by using JDBC and
     * handles any exceptions that may occur during the connection
     * by printing an error message.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     *
     * Code to close the connection to a database and
     * handle any exceptions that may occur at closure
     * by printing an error message.
     */
    public void disconnect(){
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //create a table named "PLAYERS" in the database if it does not already exist.
    public void createTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS PLAYERS (\n" +
                "  PLAYERID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  NAME VARCHAR(50) NOT NULL,\n" +
                "  POINTS INTEGER\n" +
                ")";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}