package org.campusmolndal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DBQueryTest {

    @Test
    void createTODOTable() {
        // Arrange
        String expected = "CREATE TABLE IF NOT EXISTS TODO (\n" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  DESCRIPTION VARCHAR(50) NOT NULL,\n" +
                "  PROGRESS INTEGER,\n" +
                "  ASSIGNEDTO INTEGER," +
                "  FOREIGN KEY (PROGRESS) REFERENCES Progress(ID),\n" +
                "  FOREIGN KEY (ASSIGNEDTO) REFERENCES USER(ID)" +
                ")";

        // Act
        String actual = DBQuery.createTODOTable();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void createProgressTable() {
        // Arrange
        String expected = "CREATE TABLE IF NOT EXISTS PROGRESS (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "STATUS VARCHAR(50) NOT NULL,\n"+
                "  FOREIGN KEY (ID) REFERENCES TODO(PROGRESS)" +
                ")";

        // Act
        String actual = DBQuery.createProgressTable();

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void createUserTable() {
        // Arrange
        String expected = "CREATE TABLE IF NOT EXISTS USER (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "NAME VARCHAR(50) NOT NULL,\n"+
                "AGE Integer,\n"+
                "  FOREIGN KEY (ID) REFERENCES TODO(ASSIGNEDTO)" +
                ")";

        // Act
        String actual = DBQuery.createUserTable();

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void checkIfProgresshasData() {
        // Arrange
        String expected = "SELECT COUNT(ID) AS COUNT FROM PROGRESS WHERE ID IN(1,2)"

        // Act
        String actual = DBQuery.checkIfProgresshasData();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setupTODOProgress() {
        // Arrange
        String expected = "INSERT INTO PROGRESS (STATUS) VALUES ('TODO')";

        // Act
        String actual = DBQuery.setupTODOProgress();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setupDONEProgress() {
        // Arrange
        String expected =  "INSERT INTO PROGRESS (STATUS) VALUES ('DONE')";

        // Act
        String actual = DBQuery.setupDONEProgress();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void showAllTODO() {
        // Arrange
        String expected = "SELECT\n" +
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
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS ";;

        // Act
        String actual = DBQuery.showAllTODO();

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void showALLData() {
        // Arrange
        String expected = "SELECT *\n" +
                "FROM TODO\n" +
                "Left JOIN USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN PROGRESS ON PROGRESS.ID = TODO.PROGRESS\n" +
                "WHERE DESCRIPTION NOT NULL";

        // Act
        String actual = DBQuery.showALLData();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void singleUserTodoList() {
        // Arrange
        String expected = "SELECT\n" +
                "TODO.*,\n" +
                " USER.*,\n" +
                "PROGRESS.STATUS\n" +
                "FROM\n" +
                "TODO\n" +
                "Left  JOIN\n" +
                "USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS";

        // Act
        String actual = DBQuery.singleUserTodoList();

        // Assert
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,100})
    void showONETODO(int value) {
        // Arrange
        String expected ="SELECT\n" +
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
                "WHERE TODO.ID = "+ value;

        // Act
        String actual = DBQuery.showONETODO(value);

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,100})
    void showSingleUser(int value) {
        // Arrange
        String expected = "SELECT\n" +
                "*" +
                " FROM\n" +
                " TODO\n" +
                "Left  JOIN\n" +
                "USER ON USER.ID = TODO.AssignedTo\n" +
                "Left JOIN \n" +
                "PROGRESS ON PROGRESS.ID = TODO.PROGRESS \n" +
                "WHERE TODO.AssignedTo = "+ value;

        // Act
        String actual = DBQuery.showSingleUser(value);

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void showALLAssignedTODO() {
        // Arrange
        String expected ="SELECT TODO.ID AS TODOID,\n" +
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

        // Act
        String actual = DBQuery.ShowALLAssignedTODO();

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,100})
     void showSpecificUsersTODO(int value) {
        // Arrange
        String expected = "SELECT\n" +
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
                "WHERE USER.ID = \""+ value +"\"";
        // Act
        String actual = DBQuery.showSpecificUsersTODO(value);

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void showOnlyUsers() {
        // Arrange
        String expected ="SELECT * FROM  USER";

        // Act
        String actual = DBQuery.showOnlyUsers();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void addDataToTODO() {
        // Arrange
        String expected = "INSERT INTO TODO (DESCRIPTION, PROGRESS,ASSIGNEDTO) VALUES (?,1,?)"

        // Act
        String actual = DBQuery.addDataToTODO();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void addDataToUser() {
        // Arrange
        String expected =  "INSERT INTO USER (NAME, AGE) VALUES (?,?)"
        // Act
        String actual = DBQuery.addDataToUser();

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource( {"Todo,Progress,1",
            "USER,Name,Wasabi," +
                    "TODO,Assigned,3"})
    @Test
    void updateTODOTable(String value,String value2,int value3) {
        // Arrange
        String expected ="UPDATE "+value+" SET " +value2 +" = "+ " ? " +" WHERE ID = "+ value3;

        // Act
        String actual = DBQuery.updateTODOTable(value,value2,value3);

        // Assert
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,100})
    void deleteData(String value) {
        // Arrange
        String expected = "DELETE FROM " + value + " WHERE ID = ?";

        // Act
        String actual = DBQuery.deleteData(value);

        // Assert
        assertEquals(expected, actual);
    }
}