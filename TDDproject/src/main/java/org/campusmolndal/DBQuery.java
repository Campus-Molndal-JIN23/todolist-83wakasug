package org.campusmolndal;

public class DBQuery {

    public static String createTODOTable (){

        return  "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  DESCRIPTION VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  ASSIGNEDTO INTEGER," +
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ID),\n" +
                "  FOREIGN KEY (ASSIGNEDTO) REFERENCES USER(ID)" +
                ")";

    }

    public static String createProgressTable(){
        return "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL,\n"+
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

   public static String checkIfProgresshasData(){
        return "SELECT COUNT(ID) AS COUNT FROM PROGRESS WHERE ID IN(1,2)";
   }

    public static String setupTODOProgress(){
        return "INSERT INTO PROGRESS (STATUS) VALUES ('TODO')";
    }

    public static String setupDONEProgress(){
        return  "INSERT INTO PROGRESS (STATUS) VALUES ('DONE')";
    }

    public static String showAllTODO(){
        return "SELECT\n" +
                "  TODO.*,\n" +
                "  USER.NAME,\n" +
                "  USER.AGE,\n" +
                "  PROGRESS.STATUS\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "Left  JOIN\n" +
                "  USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS ";
    }

    public static String showALLData(){
       return "SELECT *\n" +
               "FROM TODO\n" +
               "Left JOIN USER ON USER.ID = TODO.AssignedTo\n" +
               "Left JOIN PROGRESS ON PROGRESS.ID = TODO.PROGRESS\n" +
               "WHERE DESCRIPTION NOT NULL";
    }

    public static String singleUserTodoList(){
        return "SELECT\n" +
                "TODO.*,\n" +
                " USER.*,\n" +
                "PROGRESS.STATUS\n" +
                "FROM\n" +
                "TODO\n" +
                "Left  JOIN\n" +
                "USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS";
    }

    public static String showONETODO(int id){
       return "SELECT\n" +
               "TODO.ID,\n" +
               "TODO.DESCRIPTION,\n" +
               "TODO.AssignedTo,\n" +
               "TODO.PROGRESS,\n"+
               "USER.NAME,\n" +
               "USER.AGE,\n" +
               "PROGRESS.STATUS\n" +
               " FROM\n" +
               " TODO\n" +
               "Left  JOIN\n" +
               "USER ON USER.ID = TODO.AssignedTo\n" +
               "Left JOIN \n" +
               "PROGRESS ON PROGRESS.ID = TODO.PROGRESS \n" +
               "WHERE TODO.ID = "+ id;

    }

    public static String showSingleUser(int id){
        return "SELECT\n" +
                "*" +
                " FROM\n" +
                " TODO\n" +
                "Left  JOIN\n" +
                "USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS \n" +
                "WHERE TODO.AssignedTo = "+ id;

    }


    public static String ShowALLAssignedTODO(){
        return "SELECT TODO.ID AS TODOID,\n" +
                "TODO.DESCRIPTION,\n" +
                "TODO.AssignedTo,\n" +
                "USER.*,\n" +
                "PROGRESS.STATUS\n" +
                "FROM\n" +
                "TODO\n" +
                "RIGHT  JOIN\n" +
                "USER ON USER.ID = TODO.AssignedTo\n" +
                "RIGHT JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS \n" +
                "WHERE TODO.AssignedTo IS NOT NULL";
    }

    public static String showSpecificUsersTODO(int id){
        return "SELECT\n" +
                "  TODO.*,\n" +
                "  USER.*,\n" +
                "  PROGRESS.*\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "LEFT  JOIN\n" +
                "  USER ON USER.ID = TODO.AssignedTo\n" +
                "LEFT JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS \n" +
                "WHERE USER.ID = \""+ id +"\"";

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

   public static String updateTODOTable(String table,String colum,int oldStatus){
       return  "UPDATE "+table+" SET " +colum +" = "+ " ? " +" WHERE ID = "+ oldStatus ;
   }

   public static String deleteData(String table){
       return "DELETE FROM " + table + " WHERE ID = ?";
   }

}
