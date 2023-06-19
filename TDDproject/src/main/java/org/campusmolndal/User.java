package org.campusmolndal;

public class User {

   private int id ;
   private String name;
   private int age;
   private String todos ;

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

    public String getTodos() {
        return this.todos;
    }

    public void setTodos(String todos) {
        this.todos = todos;
    }
}
