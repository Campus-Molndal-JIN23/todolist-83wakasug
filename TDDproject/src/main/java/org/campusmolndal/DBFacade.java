package org.campusmolndal;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBFacade {
SQLite sqlite;
DBCRUD dbCRUD;
Connection conn;


    public DBFacade(String dbName) {
        sqlite = new SQLite(dbName);
        conn = connectDB();
        dbCRUD = new DBCRUD(conn);
    }

    public Connection connectDB(){
        return sqlite.connection();
    }

    public boolean disconnetDB(){
        return sqlite.disConnect();
    }

    public void showALLTODO(){
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showAllTODO());
        if(allTodoList.isEmpty()){
            Text.noDataFound();
        }else{allTodoList.forEach((todo,user)->{
            todo.toString();
            user.toString();
        });
        }

    }

    public Map<Integer, Todo> showOnlyDescription(){
        Map<Integer, Todo> allDescription;
        allDescription=dbCRUD.showTodo(DBQuery.showAllTODO());
        allDescription.forEach((id,todo)->{
            System.out.println(id+": " + todo.getText());
        });
        return allDescription;
    }

    public void showONETODO (int id){
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showONETODO(id));
        allTodoList.forEach((todo,user)->{
            todo.toString();
            user.toString();
        });
    }



    public ArrayList<Todo> test(int id){
        ArrayList <Todo> list = null;
        sqlite.connection();
        //list =dbCRUD.showALLTodo(DBQuery.showAllTODO());
        return list;
    }

    public void addTODO(String value1,int value2){

        dbCRUD.addData(DBQuery.addDataToTODO(),value1,value2);

    }


    public void addUser(String name,int age){
        //dbCRUD.addTODO(SQLQuery.addDataToTODO(),name,age);
    }


}
