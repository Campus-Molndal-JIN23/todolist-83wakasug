package org.campusmolndal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    private String dbName = null;
    Connection conn;


    String url="jdbc:sqlite:" + dbName+ ".db";

    // The constructor for SQLite class calls connect() and createTable() methods.
    public SQLite(String dbName) throws SQLException {
        this.dbName = dbName;
         connect();
         initialTable();
    }
    /**
     *
     * code to connect to a database by using JDBC and
     * handles any exceptions that may occur during the connection
     * by printing an error message.
     */
    public String connect() throws SQLException {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

        if (conn != null && !conn.isClosed()) {
            return "Connection is open";
        }
        return "Something went wrong";
    }
    /**
     *
     * Code to close the connection to a database and
     * handle any exceptions that may occur at closure
     * by printing an error message.
     */
    public String disconnect() throws SQLException {
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if (conn == null || conn.isClosed()) {
            return "Connection is closed";
        }
        return "Something went wrong";
    }

    /**
     *
     * Create a table
     *
     *
     */

    private  void initialTable() throws SQLException {
        String TODOTable =  "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  NAME VARCHAR(50) NOT NULL,\n" +
                "  TEXT VARCHAR(200),\n" +
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

    private String createTable(String query) throws SQLException {


        try {
            Statement stm = conn.createStatement();
            stm.execute(query);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Table Created";
    }

}