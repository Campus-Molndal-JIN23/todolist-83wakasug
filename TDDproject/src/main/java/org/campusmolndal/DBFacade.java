package org.campusmolndal;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
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
            showResult(user,todo);
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
            showResult(user,todo);
        });
    }

    public Map<Integer, User> showOnlyUsers(){
        Map<Integer, User> allUsers;
        allUsers=dbCRUD.showALLUser(DBQuery.showOnlyUsers());

        return allUsers;
    }

    public void showUsersData(Map<Integer, User> userList){

            userList.forEach((id,user)->{
                System.out.println(id+": " + user.getName());
            });
    }

    public void updateDescription (String table,String description,String todoID,String id,String todo)  {

        try{
            dbCRUD.updateDataString( DBQuery.updateTODOTable(table,description,todoID,id),todo);
            }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void addTODO(String value1,int value2){

        dbCRUD.addData(DBQuery.addDataToTODO(),value1,value2);

    }


    public void addUser(String name,int age){
        //dbCRUD.addTODO(SQLQuery.addDataToTODO(),name,age);
    }

    public void showResult(User user,Todo todo){
        if(user.getName() == null){
            System.out.println(todo.toString()+ " "+"User: Not Registered");
        }
        else{
            System.out.println(todo.toString()+ " "+user.toString());
        }
        System.out.println("");
    }

}
