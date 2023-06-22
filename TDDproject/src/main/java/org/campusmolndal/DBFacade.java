package org.campusmolndal;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBFacade {
SQLite sqlite;
DBCRUD dbCRUD;


    public DBFacade(String dbName) {
        sqlite = new SQLite(dbName);
        dbCRUD = new DBCRUD(sqlite);

    }

    public Connection connectDB(){
        return sqlite.connection();
    }

    public boolean disconnetDB(){
        return sqlite.disConnect();
    }

    public void showALLTODO(){
        Map<Todo, User> allTodoList ;
        sqlite.connection();
        allTodoList =dbCRUD.showALLTodo(DBQuery.showAllTODO());
        sqlite.disConnect();
        allTodoList.forEach((todo,user)->{
            todo.toString();
            user.toString();
        });
    }



    public ArrayList<Todo> test(int id){
        ArrayList <Todo> list = null;
        sqlite.connection();
        //list =dbCRUD.showALLTodo(DBQuery.showAllTODO());
        sqlite.disConnect();
        return list;
    }

    public void addTODO(String value1,int value2){
        sqlite.connection();
        dbCRUD.addData(DBQuery.addDataToTODO(),value1,value2);
        sqlite.disConnect();

    }

    public void addUser(String name,int age){
        //dbCRUD.addTODO(SQLQuery.addDataToTODO(),name,age);
    }

}
