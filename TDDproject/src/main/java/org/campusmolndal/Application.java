package org.campusmolndal;

import java.util.Map;

public class Application {

    DBFacade dbFacade;
    private final String todoTable = "TODO";
    private final String userTable = "USER";
    private final String columDescription = "DESCRIPTION";
    private final String columID="ID";
    private final String columStatus = "Status";
    private final String columAssignedTo = "AssignedTo";
    private Map<Integer, User> usersList;
    private Map<Todo, User> allTodoList ;
    private Map<Integer, Todo> toDoList;

    public Application (){
        dbFacade = new DBFacade("TODO");
        usersList = dbFacade.showUsersList();
        allTodoList = dbFacade.showALLTODOList();
        toDoList = dbFacade.showOnlyDescription();
    }

    public void start(){
        mainMenu();

    }

    public void mainMenu(){
        boolean run = true;
        while(run) {
            run = false;
            Text.mainMenu();
            int input = Input.number();

            switch (input) {
                case 1: showDataMenu();
                    break;
                case 2:addDataMenu();
                    break;
                case 3:updateDataMenu();
                    break;
                case 4:deleteDataMenu();
                    break;
                case 5: run = false;
                default: Text.wrongInput();
            }
        }
    }

    public void showDataMenu(){
        boolean run = true;
        int input;
        while(run) {
            Text.showDataMenu();
            input = Input.number();

            switch (input) {

                case 1:showAllTODO();
                    break;
                case 2:ShowSingleTODO();
                    break;
                case 3:showAllUsers();
                    break;
                case 4:showSingleUser();
                    break;
                case 5 : run =false;
                        mainMenu();
                    break;
                    default: Text.wrongInput();

            }
        }

    }

    private void showAllTODO(){
       dbFacade.showALLTODO(allTodoList);
    }

    private void ShowSingleTODO(){
        int id;
        Text.whichDescripion();
        Map<Integer, Todo>  list =dbFacade.showOnlyDescription();

        if(list.isEmpty()){
            noDataFound();
        }

        Text.inputNumber();
        Todo todo = null;

        try{
             todo = list.get(Input.number());
            id=todo.getId();
            dbFacade.showONETODO(id);
        }
            catch(Exception e){
                noDataFound();
        }
    }

    public void showAllUsers(){

        dbFacade.showAllUsers(usersList);
    }

    public void showSingleUser(){
        showAllUsers();
        dbFacade.showSingleUser(getUserID(usersList));
    }

    public void addDataMenu(){
        boolean run = true;
        while(run) {
            Text.addDataMenu();
            int input = Input.number();

            switch (input) {
                case 1:addTODOData();
                    break;
                case 2:addUser();
                    break;
                case 3:run = false;
                    mainMenu();
                    break;

                default: Text.wrongInput();
            }
        }
    }

    public void addTODOData(){
        Integer nameId;
        User user;
        Text.inputTodo();
        String description = Input.Str();
        usersList= dbFacade.showOnlyUsers();

        if(usersList.isEmpty()){
            nameId = 0 ;
        }
        else{
            Text.choseName();
            showAllUsers();
            Text.inputNumber();
            int number = Input.number();
            user = usersList.get(number);

            nameId= user.getId();
        }

        dbFacade.addTODO(description,nameId);
    }

    public void addUser(){
        Text.inputName();
        String name = Input.Str();
        Text.inputAge();
        int age = Input.number();
        dbFacade.addUser(name,age);

    }

    public void updateDataMenu(){

        boolean run = true;
        while(run) {
            Text.updateDataMenu();
            int input = Input.number();

            switch (input) {
                case 1:updateTODOList();
                    break;
                case 2:updateUser();
                    break;
                case 3:run = false;
                    mainMenu();
                    break;
                default: Text.wrongInput();
            }
        }
    }

    public void updateTODOList(){

        boolean run = true;
        while(run) {
            Text.updateTODOList();
            int input = Input.number();

            switch (input) {
                case 1:updateTODO();
                    break;
                case 2:updateStatus();
                    break;
                case 3:updateAssignedUser();
                    break;
                case 4:run = false;
                    mainMenu();
                    break;

                default: Text.wrongInput();
            }
        }
    }

    public void updateTODO(){
        Map<Integer, Todo> toDoList;
        int toDoid;

        Text.choseTodo();
        toDoList= dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noDataFound();
        }
        else{
            try {
                toDoid = getTodoID(toDoList);
                Text.inputTodo();
                String newDescription = Input.Str();
                dbFacade.updateDescription(todoTable, columDescription, columID, toDoid, newDescription);
            }catch (Exception e) {
                Text.noDataFound();
            }
        }
    }

    public void updateStatus(){
        Map<Integer, Todo> toDoList;
        int toDoid;
        int status;

        toDoList= dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noDataFound();
        }
        else{
            try {
                toDoid = getTodoID(toDoList);
                status = choseStatus();
                dbFacade.updateStatus(todoTable, columStatus, columID, toDoid, status);
            }catch (Exception e) {
                Text.noDataFound();
            }
        }
    }

    public int choseStatus(){
       int choice = 0;
       boolean run = true;

       while(run){
           Text.statusChoice();
           choice =Input.number();
           if(choice == 1 || choice == 2){
               run =false;
           }
       }
       return choice;
    }

    public void updateAssignedUser(){
       showAllTODO();
        if(usersList.isEmpty()){
            Text.noDataFound();
        }else {
            int todoID =getTodoID(toDoList);

            showAllUsers();
            int userId = getUserID(usersList);

            if (usersList.isEmpty()) {
                Text.noDataFound();
            } else {
                try {
                    dbFacade.updateStatus(todoTable, columAssignedTo, columID,todoID , userId);
                } catch (Exception e) {
                    Text.noDataFound();
                }
            }
        }
    }

    public void updateUser(){

        boolean run = true;
        while(run) {
            Text.updateUser();
            int input = Input.number();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:run = false;
                    mainMenu();
                    break;

                default: Text.wrongInput();

            }
        }
    }
    public void deleteDataMenu(){

        boolean run = true;
        while(run) {
            run = false;
            Text.deleteDataMenu();
            int input = Input.number();

            switch (input) {
                    case 1:deleteTodo();
                    break;
                    case 2:
                    break;

                    case 3:run = false;
                        mainMenu();
                    break;
                default: Text.wrongInput();
                    run = true;

            }
        }
    }

    public void deleteTodo(){
        Map<Integer, Todo> toDoList ;
        Todo todo;
        int toDoid;

        Text.choseTodo();
        toDoList=dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noDataFound();
        }
        else{
            try {
                toDoid = getTodoID(toDoList);
                dbFacade.deleteData(todoTable, toDoid);
            }catch (Exception e) {
                Text.noDataFound();
            }
        }
    }

    public void noDataFound(){
        Text.noDataFound();
        mainMenu();
    }

    public Integer getTodoID(Map<Integer, Todo> toDoList){
        Todo todo;
        Text.choseTodo();
        Text.inputNumber();
        int number = Input.number();
       try {
           todo = toDoList.get(number);
       } catch (Exception e){
           Text.noDataFound();
           return null;
       }
        return todo.getId();
    }

    public Integer getUserID(Map<Integer, User> userList){
        User user;
        Text.choseTodo();
        Text.inputNumber();
        int number = Input.number();
        try {
            user = userList.get(number);
        } catch (Exception e){
            Text.noDataFound();
            return null;
        }
        return user.getId();
    }

}
