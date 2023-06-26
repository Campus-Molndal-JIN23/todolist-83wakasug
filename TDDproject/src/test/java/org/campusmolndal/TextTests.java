package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Fick Hjälp från https://www.baeldung.com/java-testing-system-out-println

public class TextTests {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void testMainMenu() {
        // Arrange
        String expected = "Please Enter Number\r\n" +
                "1:Show data\r\n" +
                "2:Add data\r\n" +
                "3:Update data\r\n" +
                "4:Delete Data\r\n" +
                "5: Close program\r\n";

        Text.mainMenu();
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void testShowDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:Show ALL TODO List\r\n" +
                "2:Show a TODO List\r\n" +
                "3:Show ALL Users\r\n" +
                "4:Show a user\r\n" +
                "5:Go back to Main Menu\r\n";

        // Act
        Text.showDataMenu();

        //Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testAddDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:Add a TODO List\r\n" +
                "2:Add a user\r\n" +
                "3:Go back to Main Menu\r\n";

        // Act
        Text.addDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testUpdateDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:Update a TODO List\r\n" +
                "2:Update a user\r\n" +
                "3:Go back to Main Menu\r\n";

        // Act
        Text.updateDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testUpdateTODOList() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:update Description\r\n" +
                "2:update status\r\n" +
                "3:update assignedTo\r\n" +
                "4:Go back to Main Menu\r\n";

        // Act
        Text.updateTODOList();

        // Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:update Name\r\n" +
                "2:Update Age\r\n" +
                "3:Go back to Main Menu\r\n";

        // Act
        Text.updateUser();

        // Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testDeleteDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\r\n" +
                "1:Delete a TODO\r\n" +
                "2:Delete a user\r\n" +
                "3:Go back to Main Menu\r\n";

        // Act
        Text.deleteDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void testShowData() {
        // Arrange
        String description = "Sample Description";
        String progress = "Sample Progress";
        String status = "Sample Status";
        String user = "Sample User";
        int age = 25;
        String expected = "Description: Sample Description\r\n" +
                "Progress: Sample Progress\r\n" +
                "Status: Sample Status\r\n" +
                "Name: Sample User Age: 25";

        // Act
        Text.showData(description, progress, status, user, age);

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }


    @Test
    public void testWhichDescription() {
        // Arrange
        String expected = "Which TODO details do you want to check?";

        // Act
        Text.whichDescripion();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testChoseTodo() {
        // Arrange
        String expected = "Chose ToDO";

        // Act
        Text.choseTodo();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testNoDataFound() {
        // Arrange
        String expected = "Data not Found";

        // Act
        Text.noDataFound();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputNumber() {
        // Arrange
        String expected = "Please input Number";

        // Act
        Text.inputNumber();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testChoseUser() {
        // Arrange
        String expected = "Chose User";

        // Act
        Text.choseUser();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testChoseName() {
        // Arrange
        String expected = "Who do you want to assign to TODO?";

        // Act
        Text.choseName();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputName() {
        // Arrange
        String expected = "Enter Name";

        // Act
        Text.inputName();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputNewName() {
        // Arrange
        String expected = "Enter New Name";

        // Act
        Text.inputNewName();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputNewAge() {
        // Arrange
        String expected = "Enter New Age";

        // Act
        Text.inputNewAge();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputAge() {
        // Arrange
        String expected = "Enter Age";

        // Act
        Text.inputAge();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testInputTodo() {
        // Arrange
        String expected = "Enter Todo";

        // Act
        Text.inputTodo();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testWrongInput() {
        // Arrange
        String expected = "Wrong Input. Returning to menu.";

        // Act
        Text.wrongInput();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testStatusChoice() {
        // Arrange
        String expected = "Enter number\r\n1:TODO\r\n2:DONE";

        // Act
        Text.statusChoice();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }


}
