package org.campusmolndal;

public class SQLQuery {

    public static String createTODOTable (){

        String DESCRIPTIONTable =  "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  DESCRIPTION VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  ASSIGNEDTO INTEGER," +
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ProgressID)" +
                "  FOREIGN KEY (ASSIGNEDTO) REFERENCES USER(USERID)" +
                ")";

            return DESCRIPTIONTable;
    }

    public static String createProgressTable(){
        String progressTable = "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ProgressID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL"+
                "  FOREIGN KEY (ProgressID) REFERENCES TODO(PROGRESS)" +
                ")";

        return progressTable;
    }

    public static String createUserTable(){
        String userTable = "CREATE TABLE IF NOT EXISTS USER (\n" +
                "USERID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME VARCHAR(50) NOT NULL,\n"+
                "AGE Integer,\n"+
                "  FOREIGN KEY (USERID) REFERENCES TODO(ASSIGNEDTO)" +
                ")";

        return userTable;
    }



    public static String setupTODOProgress(){
        String todoAddQuery ="INSERT INTO TODO.PROGRESS (STATUS) VALUES ('TODO')";
        return todoAddQuery;
    }

    public static String setupDONEProgress(){
        String doneAddQuery ="INSERT INTO TODO.PROGRESS (STATUS) VALUES ('DONE')";
        return doneAddQuery;
    }

    public static String showAllTODOData(){
        String showAllData ="SELECT\n" +
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
        return showAllData;
    }

    public static String showAllDescription(){
        String showData = "SELECT\n" +
                "  TODO.DESCRIPTION\n" +
                "FROM\n" +
                "  TODO";
        return showData;
    }

    public static String ShowALLAssignedTODO(){
        String showData = "SELECT\n" +
                "  TODO.DESCRIPTION,\n" +
                "  TODO.AssignedTo,\n" +
                "  USER.NAME,\n" +
                "  USER.AGE,\n" +
                "  PROGRESS.STATUS\n" +
                "\n" +
                "FROM\n" +
                "  TODO\n" +
                "\n" +
                "Right  JOIN\n" +
                "  USER ON USER.USERID = TODO.AssignedTo\n" +
                "RIGHT JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESSID \n" +
                "  \n" +
                "  WHERE TODO.AssignedTo IS NOT NULL;";
        return showData;
    }

    public static String showSpecificUsersTODO(String name){
        String showUsersTodo = "SELECT\n" +
                "  TODO.DESCRIPTION,\n" +
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
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESSID \n" +
                "WHERE Name = \""+name +"\"";

        return showUsersTodo;
    }

    public static String showOnlyUsers(){
        String users = "SELECT USER.NAME FROM TODO";
        return users;
    }



    public static String addDataToTODO(){
        String addTODO = "INSERT INTO TODO (DESCRIPTION, PROGRESS,ASSIGNEDTO) VALUES (?,1,?)";
        return addTODO;
    }

    public static String addDataToUser(){
        String addUser = "INSERT INTO USER (NAME, AGE) VALUES (?,?)";
        return addUser;
    }


}
