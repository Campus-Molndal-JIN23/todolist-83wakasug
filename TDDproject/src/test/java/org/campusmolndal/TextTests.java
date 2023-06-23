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
        String expectedOutput = "Please Enter Number\n" +
                "1:Show data\n" +
                "2:Add data\n" +
                "3:Update data\n" +
                "4:Delete Data\n" +
                "5: Close program\n";

        // Act
        Text.mainMenu();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testShowDataMenu() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:Show ALL TODO List\n" +
                "2:Show a TODO List\n" +
                "3:Show ALL Users\n" +
                "4:Show a user\n" +
                "5:Go back to Main Menu\n";

        // Act
        Text.showDataMenu();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testAddDataMenu() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:Add a TODO List\n" +
                "2:Add a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.addDataMenu();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testUpdateDataMenu() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:Update a TODO List\n" +
                "2:Update a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.updateDataMenu();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testUpdateTODOList() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:update Description\n" +
                "2:update status\n" +
                "3:update assignedTo\n" +
                "4:Go back to Main Menu\n";

        // Act
        Text.updateTODOList();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:update Name\n" +
                "2:Update Age\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.updateUser();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testDeleteDataMenu() {
        // Arrange
        String expectedOutput = "Please Enter Choice\n" +
                "1:Delete a TODO\n" +
                "2:Delete a user\n" +
                "3:Go back to Main Menu\n";

        // Act
        Text.deleteDataMenu();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testShowData() {
        // Arrange
        String description = "Sample Description";
        String progress = "Sample Progress";
        String status = "Sample Status";
        String user = "Sample User";
        int age = 25;
        String expectedOutput = "Description: Sample Description\n" +
                "Progress: Sample Description\n" +
                "Status: Sample Status\n" +
                "Name: Sample User Age: 25\n";

        // Act
        Text.showData(description, progress, status, user, age);

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    // Write similar tests for the other methods

    @Test
    public void testWhichDescription() {
        // Arrange
        String expectedOutput = "Which TODO details do you want to check?";

        // Act
        Text.whichDescripion();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testChoseTodo() {
        // Arrange
        String expectedOutput = "Chose ToDO";

        // Act
        Text.choseTodo();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testNoDataFound() {
        // Arrange
        String expectedOutput = "Data not Found";

        // Act
        Text.noDataFound();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputNumber() {
        // Arrange
        String expectedOutput = "Please input Number";

        // Act
        Text.inputNumber();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testChoseUser() {
        // Arrange
        String expectedOutput = "Chose User";

        // Act
        Text.choseUser();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testChoseName() {
        // Arrange
        String expectedOutput = "Who do you want to assign to assign?\n";

        // Act
        Text.choseName();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputName() {
        // Arrange
        String expectedOutput = "Enter Name";

        // Act
        Text.inputName();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputNewName() {
        // Arrange
        String expectedOutput = "Enter New Name";

        // Act
        Text.inputNewName();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputNewAge() {
        // Arrange
        String expectedOutput = "Enter New Age";

        // Act
        Text.inputNewAge();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputAge() {
        // Arrange
        String expectedOutput = "Enter Age";

        // Act
        Text.inputAge();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testInputTodo() {
        // Arrange
        String expectedOutput = "Enter Todo";

        // Act
        Text.inputTodo();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testWrongInput() {
        // Arrange
        String expectedOutput = "Wrong Input. Please Enter Again.";

        // Act
        Text.wrongInput();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    public void testStatusChoice() {
        // Arrange
        String expectedOutput = "Enter number\n1:TODO\n2:DONE";

        // Act
        Text.statusChoice();

        // Assert
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @BeforeEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
