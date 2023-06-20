package org.campusmolndal;

import java.sql.Connection;

public class DBFacade {
SQLite sqlite;

    public DBFacade(String dbName) {
        sqlite = new SQLite(dbName);
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

}
