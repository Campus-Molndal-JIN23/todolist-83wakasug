package org.campusmolndal;

public class Todo {



   private int id ;
   private String text ;
   private String  done ;
   private String assignedTo;

    public Todo(int id,String description,String status,String assignedTo) {
        this.id = id;
        this.text = description;
        this.done = status;
        this.assignedTo = assignedTo;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDone() {
        return this.done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
