package org.campusmolndal;

public class DBQuery {

    public static String createTODOTable (){

        return """
                CREATE TABLE IF NOT EXISTS TODO (
                 ID INTEGER PRIMARY KEY AUTOINCREMENT,
                  DESCRIPTION VARCHAR(50) NOT NULL,
                  PROGRESS INTEGER,
                  ASSIGNEDTO INTEGER,  FOREIGN KEY (PROGRESS) REFERENCES Progress(ID),
                  FOREIGN KEY (ASSIGNEDTO) REFERENCES USER(ID))""";

    }

    public static String createProgressTable(){
        return """
                CREATE TABLE IF NOT EXISTS PROGRESS (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                STATUS VARCHAR(50) NOT NULL,
                  FOREIGN KEY (ID) REFERENCES TODO(PROGRESS))""";
    }

    public static String createUserTable(){
        return """
                CREATE TABLE IF NOT EXISTS USER (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                NAME VARCHAR(50) NOT NULL,
                AGE Integer,
                  FOREIGN KEY (ID) REFERENCES TODO(ASSIGNEDTO))""";
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
        return """
                SELECT
                  TODO.*,
                  USER.NAME,
                  USER.AGE,
                  PROGRESS.STATUS

                FROM
                  TODO

                Left  JOIN
                  USER ON USER.ID = TODO.AssignedTo
                Left JOIN\s
                PROGRESS ON PROGRESS.ID = TODO.PROGRESS\s""";
    }

    public static String showALLData(){
       return """
               SELECT *
               FROM TODO
               Left JOIN USER ON USER.ID = TODO.AssignedTo
               Left JOIN PROGRESS ON PROGRESS.ID = TODO.PROGRESS
               WHERE DESCRIPTION NOT NULL""";
    }

    public static String singleUserTodoList(){
        return """
                SELECT
                TODO.*,
                 USER.*,
                PROGRESS.STATUS
                FROM
                TODO
                Left  JOIN
                USER ON USER.ID = TODO.AssignedTo
                Left JOIN\s
                PROGRESS ON PROGRESS.ID = TODO.PROGRESS""";
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
        return """
                SELECT TODO.ID AS TODOID,
                TODO.DESCRIPTION,
                TODO.AssignedTo,
                USER.*,
                PROGRESS.STATUS
                FROM
                TODO
                RIGHT  JOIN
                USER ON USER.ID = TODO.AssignedTo
                RIGHT JOIN\s
                PROGRESS ON PROGRESS.ID = TODO.PROGRESS\s
                WHERE TODO.AssignedTo IS NOT NULL""";
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
