package org.campusmolndal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite implements Database {

    private String dbName = null;
    Connection conn;


    String url="jdbc:sqlite:" + dbName+ ".db";

    // The constructor for SQLite class calls connect() and createTable() methods.
    public SQLite(String dbName) {
        this.dbName = dbName;
         connection();
         initialTable();
    }
    /**
     *
     * code to connect to a database by using JDBC and
     * handles any exceptions that may occur during the connection
     * by printing an error message.
     */



    public Connection connection() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        try{
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     *
     * Code to close the connection to a database and
     * handle any exceptions that may occur at closure
     * by printing an error message.
     */
    public boolean disConnect(){
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try{
            if (conn == null || conn.isClosed()) {
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     *
     * Create tables
     *
     *
     */

    private void initialTable() {
        String TODOTable =  "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  TODO VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  AssignedTo INTEGER," + // Add comma here
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ProgressID)" +
                ")";

        String progress = "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ProgressID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL"+
                ")";

        createTable(TODOTable);
        createTable(progress);

    }


    public boolean createTable(String query) {


        try {
            Statement stm = conn.createStatement();
            stm.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}