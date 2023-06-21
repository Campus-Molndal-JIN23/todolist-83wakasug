package org.campusmolndal;

import java.util.ArrayList;

public class User {

   private int id ;
   private String name;
   private int age;
   private ArrayList <String > todos ;

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

    public ArrayList<String> getTodos() {
        return this.todos;
    }

    public void setTodos(ArrayList<String>todos) {
       this.todos = todos;
    }
}
