package org.campusmolndal;

public class SQLQuery {

    public String createTODOTable (){

        String DESCRIPTIONTable =  "CREATE TABLE IF NOT EXISTS DESCRIPTION (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  TODO VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  AssignedTo INTEGER," + // Add comma here
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ProgressID)" +
                ")";

            return DESCRIPTIONTable;
    }

    public String createProgress(){
        String progress = "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ProgressID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL"+
                ")";

        return progress;
    }

    public String createUserTable(){
        String progress = "CREATE TABLE IF NOT EXISTS USER (\n" +
                "USERID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL"+
                ")";

        return progress;
    }

    public String addDataToTODO(){
        String addTODO = "INSERT INTO TODO (TODO, PROGRESS) VALUES (?, ?)";

        return addTODO;
    }
}
