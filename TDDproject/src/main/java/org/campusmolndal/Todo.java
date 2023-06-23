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
        if (done.equals("1")) {
            return "TODO";
        } else if (done.equals("2")) {
            return "DONE";
        } else {
            return "Not Registered";
        }
    }
    public String toString(){
        return "\nTODO: "+ getText() + "\nProgress: "+ getProgress();
    }
}
