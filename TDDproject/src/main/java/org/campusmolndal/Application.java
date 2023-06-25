package org.campusmolndal;

import java.util.Map;

public class Application {

    DBFacade dbFacade;
    private final String todoTable = "TODO";
    private final String userTable = "USER";
    private final String columDescription = "DESCRIPTION";
    private final String columID="ID";
    private final String columProgress = "PROGRESS";
    private final String columAssignedTo = "AssignedTo";
    private final String columName = "NAME";
    private Map<Integer, User> usersList;
    private Map<Todo, User> allTodoList ;
    private Map<Integer, Todo> toDoList;

    public Application (){
        dbFacade = new DBFacade("TODODB");
        usersList = dbFacade.showUsersList();
        //allTodoList = dbFacade.showALLTODOList();
        //toDoList = dbFacade.showOnlyDescription();
    }

    public void start(){
        mainMenu();
    }

    public void mainMenu(){
        boolean run = true;
        while(run) {
            Text.mainMenu();
            run = mainMenuChoice();
        }
    }

    public boolean mainMenuChoice(){
        int input = Input.number();
        switch (input) {
            case 1 -> showDataMenu();
            case 2 -> addDataMenu();
            case 3 -> updateDataMenu();
            case 4 -> deleteDataMenu();
            case 5 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void showDataMenu(){
        boolean run = true;
        while(run) {
            Text.showDataMenu();
            run = dataMenuChoice();
        }
    }

    public boolean dataMenuChoice(){
        int input = Input.number();

        switch (input) {
            case 1 -> showAllTODO();
            case 2 -> ShowSingleTODO();
            case 3 -> showAllUsers();
            case 4 -> showSingleUser();
            case 5 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void showAllTODO(){
        dbFacade.showALLTODO(dbFacade.showALLTODOList());
    }

    public void ShowSingleTODO(){
        int id;

        Map<Integer, Todo>  list =dbFacade.showOnlyDescription();

        if(list.isEmpty()){
            Text.noTodoFound();
        }else{
            Text.whichDescripion();
            Text.inputNumber();
            Todo todo = null;

            try{
                todo = list.get(Input.number());
                id=todo.getId();
                dbFacade.showONETODO(id);
            }
            catch(Exception e){
                Text.noDataFound();
            }
        }

    }

    public void showAllUsers(){

        dbFacade.showAllUsers(dbFacade.showUsersList());
    }

    public void showSingleUser(){
        showAllUsers();
        if(!usersExists()){
            return;
        }

        int userId = getUserID(dbFacade.showUsersList());
        if(userId != 0) {
            dbFacade.showSingleUser(userId);
        }
        else{
            Text.wrongInput();
        }
    }

    public void addDataMenu(){
        boolean run = true;
        while(run) {
            Text.addDataMenu();
            run = addDataMenuChoice();
        }
    }
    public boolean addDataMenuChoice(){
        int input = Input.number();

        switch (input) {
            case 1 -> addTODOData();
            case 2 -> addUser();
            case 3 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void addTODOData(){
        Integer nameId;
        User user;
        Text.inputTodo();
        String description = Input.Str();
        usersList= dbFacade.showUsersList();

        if(usersList.isEmpty()){
            nameId = 0 ;
        }
        else{
            Text.choseName();
            showAllUsers();
            Text.inputNumber();
            int number = Input.number();
            user = usersList.get(number);
            if(user == null){
                Text.noUserFound();
                return;
            }
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
            run = updateDataMenuChoice();
        }
    }

    public boolean updateDataMenuChoice(){
        int input = Input.number();

        switch (input) {
            case 1:updateTODOList();
                break;
            case 2:updateUser();
                break;
            case 3:return false;
            default: Text.wrongInput();
        }
        return true;
    }

    public void updateTODOList(){

        boolean run = true;
        while(run) {
            Text.updateTODOList();
            run = updateTODOListChoice();
        }
    }

    public boolean updateTODOListChoice(){
        int input = Input.number();

        switch (input) {
            case 1 -> updateTODO();
            case 2 -> updateStatus();
            case 3 -> updateAssignedUser();
            case 4 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void updateTODO(){
        Map<Integer, Todo> toDoList;
        int toDoId;


        toDoList= dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noTodoFound();
        }
        else{
            try {
                toDoId = getTodoID(toDoList);
                if(toDoId == 0){
                    Text.noTodoFound();
                    return;
                }
                Text.inputTodo();
                String newDescription = Input.Str();
                dbFacade.updateString(todoTable, columDescription, toDoId, newDescription);
            }catch (Exception e) {
                Text.noDataFound();
            }
        }
    }

    public void updateStatus(){
        Map<Integer, Todo> toDoList;
        int toDoId;
        int status;

        toDoList= dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noTodoFound();
        }
        else{
            try {
                toDoId = getTodoID(toDoList);
                if(toDoId == 0){
                    Text.noTodoFound();
                    return;
                }
                status = choseStatus();
                dbFacade.updateInt(todoTable, columProgress, toDoId, status);
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
        if(!usersExists()){
            return;
        }
        Map<Integer, Todo>  list =dbFacade.showOnlyDescription();

        if(list.isEmpty()){
            Text.noTodoFound();
            return;
        }
        usersList = dbFacade.showUsersList();

        if(usersList.isEmpty()){
            Text.noUserFound();
        }else {

            int toDoId =getTodoID(list);
            if(toDoId == 0){
                Text.noTodoFound();
                return;
            }
            showAllUsers();
            int userID = getUserID(usersList);
            if(userID != 0) {
                try {
                    dbFacade.updateInt(todoTable, columAssignedTo,toDoId , userID);
                } catch (Exception e) {
                    Text.noDataFound();
                }
            }
            else{
                Text.wrongInput();
            }
        }
    }

    public void updateUser(){

        boolean run = true;
        while(run) {
            Text.updateUser();
            run = updateUserChoice();
        }
    }

    public boolean updateUserChoice(){
        int input = Input.number();
        switch (input) {
            case 1 -> updateName();
            case 2 -> updateAge();
            case 3 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void updateName(){
        if(!usersExists()){
            return;
        }
        Text.inputNewName();
        String newName = Input.Str();
        showAllUsers();
        int userID =getUserID(dbFacade.showUsersList());
        if(userID != 0) {
            dbFacade.updateString(userTable, columName, userID, newName);
        }
        else{
            Text.wrongInput();
        }
    }

    public void updateAge(){
        if(!usersExists()){
            return;
        }
        Text.inputNewAge();
        int newAge = Input.number();
        showAllUsers();
        int userID =getUserID(dbFacade.showUsersList());
        if(userID != 0) {
            dbFacade.updateInt(userTable,columName,userID,newAge);
        }
        else {
            Text.wrongInput();
        }
    }


    public void deleteDataMenu(){

        boolean run = true;
        while(run) {
            Text.deleteDataMenu();
            run = deleteDataMenuChoice();
        }
    }

    public boolean deleteDataMenuChoice(){
        int input = Input.number();

        switch (input) {
            case 1 -> deleteTodo();
            case 2 -> deleteUser();
            case 3 -> {
                return false;
            }
            default -> Text.wrongInput();
        }
        return true;
    }

    public void deleteTodo(){
        Map<Integer, Todo> toDoList ;
        int toDoid;


        toDoList=dbFacade.showOnlyDescription();

        if(toDoList.isEmpty()){
            Text.noTodoFound();
        }
        else{
            Text.choseTodo();
            try {
                toDoid = getTodoID(toDoList);
                dbFacade.deleteData(todoTable, toDoid);
            }catch (Exception e) {
                Text.noDataFound();
            }
        }
    }

    public void deleteUser(){
        if(!usersExists()){
            return;
        }
        try {
            Text.choseUser();
            showAllUsers();
            int userID = getUserID(dbFacade.showUsersList());
            if(userID != 0) {
                dbFacade.deleteData(userTable, userID);

            }
            else{
                Text.wrongInput();
            }
        }catch (Exception e){
            Text.noDataFound();
        }

    }


    public int getTodoID(Map<Integer, Todo> toDoList){
        Todo todo;
        Text.choseTodo();
        Text.inputNumber();
        int number = Input.number();
        try {
            todo = toDoList.get(number);
        } catch (Exception e){
            Text.noDataFound();
            return 0;
        }
        if(todo == null){
            Text.noTodoFound();
            return 0;
        }
        return todo.getId();
    }

    public int getUserID(Map<Integer, User> userList){
        User user;
        Text.choseUser();
        Text.inputNumber();
        int number = Input.number();

        try {
            user = userList.get(number);
            if(user == null){
                Text.noUserFound();
                return 0;
            }

        } catch (Exception e){
            Text.noDataFound();
            return 0;
        }

        return user.getId();
    }

    private boolean usersExists(){
        Map<Integer,User> allUsers = dbFacade.showUsersList();
        if(allUsers.isEmpty()){
            Text.noUsersExists();
            return false;
        }
        return true;
    }
}