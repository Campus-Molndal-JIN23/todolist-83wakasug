package org.campusmolndal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    public void disconnetDB(Connection conn){
         sqlite.disConnect(conn);
    }

    public Map<Todo, User> showALLTODOList(){
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showALLData());

        return allTodoList;
    }

    public void showALLTODO(Map<Todo, User> allTodoList){
        conn = connectDB();
        if(allTodoList.isEmpty()){
            Text.noDataFound();
        }else{allTodoList.forEach((todo,user)->{
            showResult(user,todo);
        });
        }
        disconnetDB(conn);
    }



    public Map<Integer, Todo> showOnlyDescription(){
        conn = connectDB();
        Map<Integer, Todo> allDescription;
        allDescription=dbCRUD.showTodo(DBQuery.showAllTODO());
        allDescription.forEach((id,todo)->{
            System.out.println("   | " + id + ": " + todo.getText() + " |");
            System.out.println("------------------------------");
        });

        disconnetDB(conn);
        return allDescription;
    }

    public void showONETODO (int id){
        conn = connectDB();
        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showONETODO(id));
        allTodoList.forEach((todo,user)->{
            showResult(user,todo);
        });

        disconnetDB(conn);
    }

    public void showSingleUser(int id){
        conn = connectDB();
        Map<Integer, User> userList ;
        User user =dbCRUD.showSingleUser(DBQuery.showSingleUser(id));
        System.out.println(user.toString());

        userList =dbCRUD.showUsers(DBQuery.showSingleUser(id));
        userList.forEach((key,todo)->{

            showSingleResult(todo);
           try{

             showSingleResult(todo);

           }catch (Exception e){
               showSingleResultNull(user);
           }
        });

        disconnetDB(conn);
    }


    public Map<Integer, User> showUsersList(){
        conn = connectDB();
        Map<Integer, User> userList = dbCRUD.showALLUser(DBQuery.showOnlyUsers());
        disconnetDB(conn);
         return userList;
    }

    public void showAllUsers(Map<Integer, User> userList ){
        userList.forEach((id,user)->{
            System.out.print("   | " + id + ": " + user.getName());
            System.out.println("  Age :"+ user.getAge() +" |");
            System.out.println("--------------------------------");
        });
    }



    public void updateString (String table,String colum,String columId,int id,String description)  {
        conn = connectDB();
        try{
            dbCRUD.updateDataString( DBQuery.updateTODOTable(table,colum,columId,id),description);
            }catch (Exception e){
            System.out.println(e.getMessage());
        }
        disconnetDB(conn);
    }

    public void updateInt (String table,String colum,String columId,int id,int StatusNo)  {
        conn = connectDB();
        try{
            dbCRUD.updateDataInt( DBQuery.updateTODOTable(table,colum,columId,id),StatusNo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        disconnetDB(conn);
    }


    public void addTODO(String value1,int value2){
        conn = connectDB();
        dbCRUD.addData(DBQuery.addDataToTODO(),value1,value2);
        disconnetDB(conn);
    }

    public void addUser(String name,int age){
        conn = connectDB();
        dbCRUD.addData(DBQuery.addDataToUser(),name,age);
        disconnetDB(conn);
    }

    public void deleteData(String table,int id){
        connectDB();
        dbCRUD.deleteData(DBQuery.deleteData(table),id);
        disconnetDB(conn);
    }


    public void showResult(User user,Todo todo){
        if (user.getName() == null) {
            System.out.println( todo.toString() + " User: Not Registered");
            System.out.println("-----------------------------------------");
        } else {
            System.out.println(todo.toString() + " " + user.toString());
            System.out.println("-----------------------------------------");
        }
    }

    public void showSingleResult(User user){

            ArrayList<Todo> todoList =user.getTodos();
            for(Todo mission:todoList){
                System.out.println("Progress: "+mission.getDone());
                System.out.println("Todo :" + mission.getText());
            }
            System.out.println("-----------------------------------------");
    }

    public void showSingleResultNull(User user){
        System.out.println( user.toString() + " Todo: No Assigment");
        System.out.println("-----------------------------------------");
    }
}
