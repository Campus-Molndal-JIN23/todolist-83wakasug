package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class ApplicationTest {

    Application mockApp;
    Input mockInput;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @BeforeEach
    void setUP() throws SQLException {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errContent));
        framework().clearInlineMocks();
        mockApp = mock(Application.class);
        doCallRealMethod().when(mockApp).start();
        doCallRealMethod().when(mockApp).mainMenuChoice();




    }


    @Test
    void start() {
        mockApp.start();
        verify(mockApp).mainMenu();

    }

    @Test
    void mainMenuChoice1() {
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.mainMenuChoice();
            verify(mockApp).showDataMenu();
        }
    }
    @Test
    void mainMenuChoice2() {
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.mainMenuChoice();
            verify(mockApp).addDataMenu();
        }
    }
    @Test
    void mainMenuChoice3() {
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            mockApp.mainMenuChoice();
            verify(mockApp).updateDataMenu();
        }
    }
    @Test
    void mainMenuChoice4() {
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(4);
            mockApp.mainMenuChoice();
            verify(mockApp).deleteDataMenu();
        }
    }
    @Test
    void mainMenuChoice5() {
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(5);
            boolean run = mockApp.mainMenuChoice();
            assertEquals(run,false);
        }
    }
    @Test
    void mainMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            boolean run = mockApp.mainMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void showDataMenu() {
    }

    @Test
    void showAllUsers() {
    }

    @Test
    void showSingleUser() {
    }

    @Test
    void addDataMenu() {
    }

    @Test
    void addTODOData() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateDataMenu() {
    }

    @Test
    void updateTODOList() {
    }

    @Test
    void updateTODO() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void choseStatus() {
    }

    @Test
    void updateAssignedUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void updateName() {
    }

    @Test
    void updateAge() {
    }

    @Test
    void deleteDataMenu() {
    }

    @Test
    void deleteTodo() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void noDataFound() {
    }

    @Test
    void getTodoID() {
    }

    @Test
    void getUserID() {
    }
}