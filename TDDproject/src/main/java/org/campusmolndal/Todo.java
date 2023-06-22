package org.campusmolndal;

public class Todo {



   private int id ;
   private String text ;
   private String  done ;
   private int assignedTo;

    public Todo(int id,String description,int assignedTo,String status) {
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

    public int getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTO(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getProgress(){
        if(Integer.parseInt(done) ==1) return "TODO";
        if(Integer.parseInt(done)  ==2) return "DONE";

        return "Not Registered";
    }
    public String toString(){
        return "TODO: "+ getText() + " Progress: "+ getProgress();
    }
}
