package org.campusmolndal;

public class DBQuery {

    public static String createTODOTable (){

        return  "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  DESCRIPTION VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  ASSIGNEDTO INTEGER," +
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ID)" +
                "  FOREIGN KEY (ASSIGNEDTO) REFERENCES USER(ID)" +
                ")";

    }

    public static String createProgressTable(){
        return "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL"+
                "  FOREIGN KEY (ID) REFERENCES TODO(PROGRESS)" +
                ")";
    }

    public static String createUserTable(){
        return "CREATE TABLE IF NOT EXISTS USER (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME VARCHAR(50) NOT NULL,\n"+
                "AGE Integer,\n"+
                "  FOREIGN KEY (ID) REFERENCES TODO(ASSIGNEDTO)" +
                ")";
    }



    public static String setupTODOProgress(){
        return "INSERT INTO TODO.PROGRESS (STATUS) VALUES ('TODO')";
    }

    public static String setupDONEProgress(){
        return  "INSERT INTO TODO.PROGRESS (STATUS) VALUES ('DONE')";
    }

    public static String showAllTODO(){
        return "SELECT\n" +
                "  TODO.DESCRIPTION,\n" +
                "  TODO.AssignedTo,\n" +
                "  USER.NAME,\n" +
                "  USER.AGE,\n" +
                "  PROGRESS.STATUS\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "Left  JOIN\n" +
                "  USER ON USER.USERID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESSID ";
    }

    public static String showONETODO(String description){
       return "SELECT\n" +
                "  TODO.DESCRIPTION,\n" +
                "  TODO.AssignedTo,\n" +
                "  USER.NAME,\n" +
                "  USER.AGE,\n" +
                "  PROGRESS.STATUS\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "Left  JOIN\n" +
                "  USER ON USER.USERID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESSID "+
                "WHERE DESCRIPTION = \""+description +"\"";

    }

    public static String showAllTODOTable(){
        return  "SELECT * FROM TODO";
    }

    public static String ShowALLAssignedTODO(){
        return "SELECT TODO.ID AS TODOID,\n" +
                "                  TODO.DESCRIPTION,\n" +
                "                  TODO.AssignedTo,\n" +
                "                  USER.NAME,\n" +
                "                  USER.AGE,\n" +
                "                  USER.ID,\n" +
                "                  PROGRESS.STATUS\n" +
                "                \n" +
                "                FROM\n" +
                "                  TODO\n" +
                "                \n" +
                "                RIGHT  JOIN\n" +
                "                  USER ON USER.ID = TODO.AssignedTo\n" +
                "                RIGHT JOIN \n" +
                "                PROGRESS ON PROGRESS.ID = TODO.PROGRESSID \n" +
                "                  \n" +
                "                  WHERE TODO.AssignedTo IS NOT NULL";
    }

    public static String showSpecificUsersTODO(String name){
        return "SELECT\n" +
                "  TODO.DESCRIPTION,\n" +
                "  USER.NAME,\n" +
                "  USER.AGE,\n" +
                "  PROGRESS.STATUS\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "RIGHT  JOIN\n" +
                "  USER ON USER.USERID = TODO.AssignedTo\n" +
                "RIGHT JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESSID \n" +
                "WHERE Name = \""+name +"\"";

    }

    public static String showOnlyUsers(){
        return "SELECT * FROM  USER";
    }


    public static String addDataToTODO(){
        return "INSERT INTO TODO (DESCRIPTION, PROGRESS,ASSIGNEDTO) VALUES (?,1,?)";
    }

    public static String addDataToUser(){
        return  "INSERT INTO USER (NAME, AGE) VALUES (?,?)";
    }

   public static String updateTODOTable(String table,String colum,String newStatus,String oldStatus){
      return  "UPDATE "+table+" SET " +colum +" = "+ " ? " +" WHERE "+ colum +" = "+ oldStatus ;

   }

   public static String deleteData(String table,String id){
        return "DELETE FROM " + table + " WHERE ID = " + id;
   }

}
