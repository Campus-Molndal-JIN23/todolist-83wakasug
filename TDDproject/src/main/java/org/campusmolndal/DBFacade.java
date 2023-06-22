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

    public Map<Todo, User> showALLTODOList(){
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showALLData());

        return allTodoList;
    }

    public void showALLTODO(Map<Todo, User> allTodoList){
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
            System.out.println("   | " + todo.getId() + ": " + todo.getText() + " |");
            System.out.println("------------------------------");
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

    public void showSingleUser(int id){
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showSpecificUsersTODO(id));
        allTodoList.forEach((todo,user)->{
            showResult(user,todo);
        });
    }


    public Map<Integer, User> showUsersList(){
        Map<Integer, User> userList = dbCRUD.showALLUser(DBQuery.showOnlyUsers());
         return userList;
    }

    public void showAllUsers(Map<Integer, User> userList ){
        userList.forEach((id,user)->{
            System.out.println("   | " + id + ": " + user.getName() + " |");
            System.out.println("--------------------");
        });
    }



    public void updateDescription (String table,String colum,String columId,int id,String description)  {

        try{
            dbCRUD.updateDataString( DBQuery.updateTODOTable(table,colum,columId,id),description);
            }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void updateStatus (String table,String colum,String columId,int id,int StatusNo)  {

        try{
            dbCRUD.updateDataInt( DBQuery.updateTODOTable(table,colum,columId,id),StatusNo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void addTODO(String value1,int value2){

        dbCRUD.addData(DBQuery.addDataToTODO(),value1,value2);

    }

    public void addUser(String name,int age){
        dbCRUD.addData(DBQuery.addDataToUser(),name,age);
    }

    public void deleteData(String table,int id){

        dbCRUD.deleteData(table,id);

    }


    public void showResult(User user,Todo todo){
        if (user.getName() == null) {
            System.out.println("   | " + todo.toString() + " User: Not Registered |");
            System.out.println("--------------------");
        } else {
            System.out.println("   | " + todo.toString() + " " + user.toString() + " |");
            System.out.println("--------------------");
        }
        System.out.println("");
    }

}
