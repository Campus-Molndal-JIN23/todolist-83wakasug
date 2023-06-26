package org.campusmolndal;

import java.sql.*;

public class SQLite implements Database {

    Connection conn;
    String url;



    public SQLite(String dbName) {
       url = "jdbc:sqlite:" + dbName+ ".db";
        connection();
        //If DB has no table create TODOTable,ProgressTable,UserTable,and set the value to Todo and DONE to Progress Table.
        initialTable(DBQuery.createTODOTable(),DBQuery.createProgressTable(),DBQuery.createUserTable(),DBQuery.setupTODOProgress(),DBQuery.setupDONEProgress());
    }


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