package org.campusmolndal;

import java.sql.Connection;
import java.util.ArrayList;
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

    public Map<Todo, User> showALLTODOList(){

        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showALLData());

        return allTodoList;
    }

    public void showALLTODO(Map<Todo, User> allTodoList){

        if(allTodoList.isEmpty()){
            Text.noTodoFound();
        }else{allTodoList.forEach((todo,user)->{
            Text.showResult(user,todo);
        });
        }
    }



    public Map<Integer, Todo> showOnlyDescription(){

        Map<Integer, Todo> allDescription;
        allDescription=dbCRUD.showTodo(DBQuery.showAllTODO());
        Text.showDescription(allDescription);

        return allDescription;
    }

    public void showONETODO (int id){

        Map<Todo, User> allTodoList ;
        allTodoList =dbCRUD.showALLTodo(DBQuery.showONETODO(id));
        allTodoList.forEach((todo,user)->{
            Text.showResult(user,todo);
        });
    }

    public void showSingleUser(int id){

        Map<Integer, User> userList ;
        User user =dbCRUD.showSingleUser(DBQuery.showSingleUser(id));
        if(user == null){
            Text.noTodoFound();
        }else{
            userList =dbCRUD.showUsers(DBQuery.showSingleUser(id));
            Text.showUserName(user);
            userList.forEach((key,todo)->{
                try{
                    showSingleResult(todo);
                }catch (Exception e){
                    showSingleResultNull(user);
                }
            });
        }
    }


    public Map<Integer, User> showUsersList(){

        Map<Integer, User> userList = dbCRUD.showALLUser(DBQuery.showOnlyUsers());
         return userList;
    }

    public void showAllUsers(Map<Integer, User> userList ){
        Text.showAllUsers(userList);
    }



    public void updateString (String table,String colum,int id,String description)  {
        try{
            dbCRUD.updateDataString( DBQuery.updateTODOTable(table,colum,id),description);
            }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateInt (String table,String colum,int id,int StatusNo)  {
        try{
            dbCRUD.updateDataInt( DBQuery.updateTODOTable(table,colum,id),StatusNo);
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

        dbCRUD.deleteData(DBQuery.deleteData(table),id);

    }



    public void showSingleResult(User user){

            ArrayList<Todo> todoList =user.getTodos();
            Text.showTodos(todoList);
    }

    public void showSingleResultNull(User user){

        Text.showSingleUserNullResults(user);
    }
}
