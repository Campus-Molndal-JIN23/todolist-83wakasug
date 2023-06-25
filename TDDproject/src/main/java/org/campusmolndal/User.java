package org.campusmolndal;

import java.util.ArrayList;

public class User {

   private int id ;
   private String name;
   private int age;
   private ArrayList <Todo > todos ;

   public User(int id,String name,int age){
       this.id = id;
       this.name = name;
       this.age = age;
   }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Todo> getTodos() {
        return this.todos;
    }

    public void setTodos(ArrayList<Todo> list) {
       todos = list;
    }

    public void addTodo(Todo todo){
       todos.add(todo);
    }

    public String toString(){
       return "\nUser Name: " + getName() + "\nAge: " + getAge();
    }

}
