package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Fick Hjälp från https://www.baeldung.com/java-testing-system-out-println

public class TextTests {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testMainMenu() {
        // Arrange
        String expected = "Please Enter Number\n" +
                "1:Show data\n" +
                "2:Add data\n" +
                "3:Update data\n" +
                "4:Delete Data\n" +
                "5: Close program\n";

        // Act
        Text.mainMenu();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testShowDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:Show ALL TODO List\n" +
                "2:Show a TODO List\n" +
                "3:Show ALL Users\n" +
                "4:Show a user\n" +
                "5:Go back to Main Menu\n";

        // Act
        Text.showDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testAddDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:Add a TODO List\n" +
                "2:Add a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.addDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testUpdateDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:Update a TODO List\n" +
                "2:Update a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.updateDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testUpdateTODOList() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:update Description\n" +
                "2:update status\n" +
                "3:update assignedTo\n" +
                "4:Go back to Main Menu\n";

        // Act
        Text.updateTODOList();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:update Name\n" +
                "2:Update Age\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.updateUser();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testDeleteDataMenu() {
        // Arrange
        String expected = "Please Enter Choice\n" +
                "1:Delete a TODO\n" +
                "2:Delete a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.deleteDataMenu();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testShowData() {
        // Arrange
        String description = "Sample Description";
        String progress = "Sample Progress";
        String status = "Sample Status";
        String user = "Sample User";
        int age = 25;
        String expected = "Description: Sample Description\n" +
                "Progress: Sample Description\n" +
                "Status: Sample Status\n" +
                "Name: Sample User Age: 25\n";

        // Act
        Text.showData(description, progress, status, user, age);

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    // Write similar tests for the other methods

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
        String expected = "Who do you want to assign to assign?\n";

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
        String expected = "Wrong Input. Please Enter Again.";

        // Act
        Text.wrongInput();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    public void testStatusChoice() {
        // Arrange
        String expected = "Enter number\n1:TODO\n2:DONE";

        // Act
        Text.statusChoice();

        // Assert
        assertEquals(expected, outputStream.toString().trim());
    }

    @BeforeEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
