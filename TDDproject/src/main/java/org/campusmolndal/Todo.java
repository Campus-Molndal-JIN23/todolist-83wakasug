package org.campusmolndal;

public class Todo {
   private int id ;
   private String text ;
   private int done ;
   private String assignedTo;

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

    public int getDone() {
        return this.done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
