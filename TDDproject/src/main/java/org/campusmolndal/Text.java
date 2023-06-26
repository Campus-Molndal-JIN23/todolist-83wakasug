package org.campusmolndal;

import java.util.ArrayList;
import java.util.Map;

public class Text {


        public static void mainMenu (){
            System.out.println("Please Enter Number");
            System.out.println("1:Show data");
            System.out.println("2:Add data");
            System.out.println("3:Update data");
            System.out.println("4:Delete Data");
            System.out.println("5: Close program");
        }

        public static void showDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Show ALL TODO List");
            System.out.println("2:Show a TODO List");
            System.out.println("3:Show ALL Users");
            System.out.println("4:Show a user");
            System.out.println("5:Go back to Main Menu");
        }

        public static void addDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Add a TODO List");
            System.out.println("2:Add a user");
            System.out.println("3:Go back to Main Menu");
        }

        public static void updateDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Update a TODO List");
            System.out.println("2:Update a user");
            System.out.println("3:Go back to Main Menu");
        }

        public static void updateTODOList(){
            System.out.println("Please Enter Choice");
            System.out.println("1:update Description");
            System.out.println("2:update status");
            System.out.println("3:update assignedTo");
            System.out.println("4:Go back to Main Menu");
        }

        public static void updateUser(){
            System.out.println("Please Enter Choice");
            System.out.println("1:update Name");
            System.out.println("2:Update Age");
            System.out.println("3:Go back to Main Menu");
        }

         public static void deleteDataMenu(){
            System.out.println("Please Enter Choice");
            System.out.println("1:Delete a TODO");
            System.out.println("2:Delete a user");
             System.out.println("3:Go back to Main Menu");
        }

        public static void showData(String description,String progress,String status,String user,int age){
            System.out.println("Description: "+ description);
            System.out.println("Progress: " +progress);
            System.out.println("Status: "+status);
            System.out.println("Name: "+ user + " Age: "+ age);
            System.out.println();

        }

        public static void whichDescripion(){
            System.out.println("Which TODO details do you want to check?");
        }
        public static void choseTodo(){
            System.out.println("Chose ToDO");
        }
        public static void noDataFound(){
        System.out.println("Data not Found");
    }
        public static void noTodoFound(){
        System.out.println("There are no todos");
    }
        public static void noUserFound(){
        System.out.println("The user doesn't exist");
    }
        public static void noUsersExists(){
        System.out.println("No user exists");
    }


        public static void inputNumber(){
            System.out.println("Please input Number");
        }

        public static void choseUser(){
            System.out.println("Chose User");
        }
        public static void choseName(){
            System.out.println("Who do you want to assign to TODO?");
            System.out.println(" ");
        }

        public static void inputName(){
        System.out.println("Enter Name");
        }
        public static void inputNewName(){
        System.out.println("Enter New Name");
    }
        public static void inputNewAge(){
            System.out.println("Enter New Age");
        }

        public static void inputAge(){
            System.out.println("Enter Age");
        }

        public static void inputTodo(){
        System.out.println("Enter Description");
        }


        public static void wrongInput(){
            System.out.println("Wrong Input. Returning to menu.");
        }

        public void test(){
            System.out.println("hej");
        }
        public static void statusChoice(){
            System.out.println("Enter number");
            System.out.println("1:TODO");
            System.out.println("2:DONE");
        }
        public static void noTodoAssigned(){
            System.out.println("User does not have any TODO!");
        }

        public static void showDescription(Map<Integer, Todo> allDescription){
            allDescription.forEach((id,todo)->{
                System.out.println("   | " + id + ": " + todo.getText() + " |");
                System.out.println("------------------------------");
            });
        }

        public static void showUserName(User user){
        System.out.println(user.toString());
    }

        public static void showTodos(ArrayList<Todo> todoList){
            for(Todo mission:todoList){
                System.out.println("Progress: "+mission.getDone());
                System.out.println("Todo :" + mission.getText());
            }
                System.out.println("-----------------------------------------");
         }

        public static void showSingleUserNullResults(User user){
            System.out.println( user.toString() + " Todo: No Assigment");
            System.out.println("-----------------------------------------");
         }

        public static void showResult(User user, Todo todo){
            if (user.getName() == null) {
                System.out.println( todo.toString() + " User: Not Registered");
                System.out.println("-----------------------------------------");
            } else {
                System.out.println(todo.toString() + " " + user.toString());
                System.out.println("-----------------------------------------");
            }
        }

    public static void showAllUsers(Map<Integer, User> userList){
        userList.forEach((id,user)->{
            System.out.print("   | " + id + ": " + user.getName());
            System.out.println("  Age :"+ user.getAge() +" |");
            System.out.println("--------------------------------");
        });
    }



}
