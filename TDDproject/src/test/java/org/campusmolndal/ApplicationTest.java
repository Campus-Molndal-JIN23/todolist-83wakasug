package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class ApplicationTest {

    Application mockApp;
    Input mockInput;

    DBFacade mockDBFacade;
    User mockUser;

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
        Mockito.framework().clearInlineMocks();
        mockApp = Mockito.mock(Application.class);
        mockDBFacade = Mockito.mock(DBFacade.class);
        mockUser = Mockito.mock(User.class);
        mockApp.dbFacade = mockDBFacade;
        doCallRealMethod().when(mockApp).start();
        doCallRealMethod().when(mockApp).mainMenuChoice();
        doCallRealMethod().when(mockApp).dataMenuChoice();
        doCallRealMethod().when(mockApp).addDataMenuChoice();
        doCallRealMethod().when(mockApp).updateTODOListChoice();
        doCallRealMethod().when(mockApp).deleteDataMenuChoice();
        doCallRealMethod().when(mockApp).updateDataMenuChoice();
        doCallRealMethod().when(mockApp).showAllUsers();
        doCallRealMethod().when(mockApp).showSingleUser();


        when(mockDBFacade.showUsersList()).thenReturn(new HashMap<Integer, User>(){{put(1,mockUser);}});

    }


    @Test
    void start() {
        mockApp.start();
        verify(mockApp).mainMenu();

    }

    @Test
    void mainMenuChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.mainMenuChoice();
            verify(mockApp).showDataMenu();
        }
    }
    @Test
    void mainMenuChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.mainMenuChoice();
            verify(mockApp).addDataMenu();
        }
    }
    @Test
    void mainMenuChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            mockApp.mainMenuChoice();
            verify(mockApp).updateDataMenu();
        }
    }
    @Test
    void mainMenuChoice4() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(4);
            mockApp.mainMenuChoice();
            verify(mockApp).deleteDataMenu();
        }
    }
    @Test
    void mainMenuChoice5() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(5);
            boolean run = mockApp.mainMenuChoice();
            assertFalse(run);
        }
    }
    @Test
    void mainMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.mainMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void dataMenuChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.dataMenuChoice();
            verify(mockApp).showAllTODO();
        }
    }
    @Test
    void dataMenuChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.dataMenuChoice();
            verify(mockApp).ShowSingleTODO();
        }
    }
    @Test
    void dataMenuChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            mockApp.dataMenuChoice();
            verify(mockApp).showAllUsers();
        }
    }
    @Test
    void dataMenuChoice4() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(4);
            mockApp.dataMenuChoice();
            verify(mockApp).showSingleUser();
        }
    }
    @Test
    void dataMenuChoice5() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(5);
            boolean run = mockApp.dataMenuChoice();
            assertFalse(run);
        }
    }
    @Test
    void dataMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.dataMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void showAllUsers() {
        mockApp.showAllUsers();
        verify(mockDBFacade).showAllUsers(any());
    }

    @Test
    void showSingleUserWhenUserExist() {
        when(mockApp.getUserID(anyMap())).thenReturn(1);
        mockApp.showSingleUser();
        outputStream.toString();
        verify(mockDBFacade).showSingleUser(anyInt());
    }
    @Test
    void showSingleUserWhenUserDoesNotExist() {
        when(mockApp.getUserID(any())).thenReturn(0);
        mockApp.showSingleUser();
        verify(mockDBFacade, never()).showSingleUser(anyInt());
    }

    @Test
    void addDataMenuChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.addDataMenuChoice();
            verify(mockApp).addTODOData();
        }
    }
    @Test
    void addDataMenuChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.addDataMenuChoice();
            verify(mockApp).addUser();
        }
    }
    @Test
    void addDataMenuChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            boolean run = mockApp.addDataMenuChoice();
            assertFalse(run);
        }
    }
    @Test
    void addDataMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.addDataMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void addTODOData() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateTODOListChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.updateTODOListChoice();
            verify(mockApp).updateTODO();
        }
    }
    @Test
    void updateTODOListChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.updateTODOListChoice();
            verify(mockApp).updateStatus();
        }
    }
    @Test
    void updateTODOListChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            mockApp.updateTODOListChoice();
            verify(mockApp).updateAssignedUser();
        }
    }
    @Test
    void updateTODOListChoice4() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(4);
            boolean run = mockApp.updateTODOListChoice();
            assertFalse(run);
        }
    }
    @Test
    void updateTODOListChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.updateTODOListChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void updateDataMenuChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.updateDataMenuChoice();
            verify(mockApp).updateTODOList();
        }
    }
    @Test
    void updateDataMenuChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.updateDataMenuChoice();
            verify(mockApp).updateUser();
        }
    }
    @Test
    void updateDataMenuChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            boolean run = mockApp.updateDataMenuChoice();
            assertFalse(run);
        }
    }
    @Test
    void updateDataMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.updateDataMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
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
    void deleteDataMenuChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.deleteDataMenuChoice();
            verify(mockApp).deleteTodo();
        }
    }
    @Test
    void deleteDataMenuChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.deleteDataMenuChoice();
            verify(mockApp).deleteUser();
        }
    }
    @Test
    void deleteDataMenuChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            boolean run = mockApp.deleteDataMenuChoice();
            assertFalse(run);
        }
    }
    @Test
    void deleteDataMenuChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.deleteDataMenuChoice();
            assertEquals(expected, outputStream.toString());
        }
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