package org.campusmolndal;

import java.sql.Connection;

public class DBFacade {
SQLite sqlite;
SQLiteCRUD sqLiteCRUD;


    public DBFacade(String dbName) {
        sqlite = new SQLite(dbName);
        sqLiteCRUD = new SQLiteCRUD(sqlite);
    }

    public Connection connectDB(){
        return sqlite.connection();
    }

    public boolean disconnetDB(){
        return sqlite.disConnect();
    }

    public String tst(){
        return "bla";

    }

    public void addTODO(String description,int assignedTo){
        sqLiteCRUD.addTODO(SQLQuery.addDataToTODO(),description,assignedTo);
    }

    public void addUser(String description,int assignedTo){
        sqLiteCRUD.addTODO(SQLQuery.addDataToTODO(),description,assignedTo);
    }

}
