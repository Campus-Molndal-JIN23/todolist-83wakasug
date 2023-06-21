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
        initialTable(SQLQuery.createTODOTable(),SQLQuery.createProgressTable(),SQLQuery.createUserTable(),SQLQuery.setupTODOProgress(),SQLQuery.setupDONEProgress());
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

    private boolean createTable(String query) {

        try {
            Statement stm = conn.createStatement();
            stm.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void readyForProgressTable(String query){
        try{
            Statement stm = conn.createStatement();
            stm.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private void initialTable(String descriptionTable,String ProgressTable,String Usertable,String addTODO,String addDone) {
        createTable(descriptionTable);
        createTable(ProgressTable);
        createTable(Usertable);
        readyForProgressTable(addDone);
        readyForProgressTable(addTODO );
    }






}