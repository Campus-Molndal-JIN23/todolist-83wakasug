package org.campusmolndal;

import java.sql.*;

public class SQLite implements Database {

    private String dbName = null;
    Connection conn;
    String url;



    // The constructor for SQLite class calls connect() and createTable() methods.
    public SQLite(String dbName) {
        this.dbName = dbName;
       url = "jdbc:sqlite:" + dbName+ ".db";
        connection();
        initialTable(DBQuery.createTODOTable(),DBQuery.createProgressTable(),DBQuery.createUserTable(),DBQuery.setupTODOProgress(),DBQuery.setupDONEProgress());
    }
    /**
     *
     * code to connect to a database by using JDBC and
     * handles any exceptions that may occur during the connection
     * by printing an error message.
     */


    public Connection connection() {

        try{
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    /**
     * Code to close the connection to a database and
     * handle any exceptions that may occur at closure
     * by printing an error message.
     *
     * @return
     */
    public boolean disConnect(Connection conn){
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    boolean createTable(String query) {

        try {
            Statement stm = conn.createStatement();
            stm.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean checkIfInsertData(String query){
        boolean hasData = false;
        try{
            Statement stm = conn.createStatement();
            ResultSet rst=stm.executeQuery(query);
            if (rst.next()) {
                int count = rst.getInt("COUNT");
                hasData = count > 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return hasData;
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

        if(!checkIfInsertData(DBQuery.checkIfProgresshasData())){
            readyForProgressTable(addTODO);
            readyForProgressTable(addDone);
        }
    }
}