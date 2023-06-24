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
import java.util.Map;

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
        doCallRealMethod().when(mockApp).updateUserChoice();

        Map<Integer, Todo> userMap = new HashMap<>();
        Todo todoTest = new Todo(1, "Homework", 3, "DONE");
        userMap.put(1, todoTest);
        when(mockDBFacade.showOnlyDescription()).thenReturn(userMap);


        when(mockDBFacade.showUsersList()).thenReturn(new HashMap<Integer, User>(){{put(1,mockUser);}});
        when(mockUser.getId()).thenReturn(1);

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
        doCallRealMethod().when(mockApp).addTODOData();
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::Str).thenReturn("test");
            input.when(Input::number).thenReturn(1);
            mockApp.addTODOData();
            verify(mockDBFacade).addTODO("test",1);
        }
    }

    @Test
    void addUser() {
        doCallRealMethod().when(mockApp).addUser();
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::Str).thenReturn("test");
            input.when(Input::number).thenReturn(1);
            mockApp.addUser();
            verify(mockDBFacade).addUser("test",1);
        }
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

        doCallRealMethod().when(mockApp).updateTODO();
        when(mockApp.getTodoID(any())).thenReturn(1);
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::Str).thenReturn("test");
            mockApp.updateTODO();
            verify(mockDBFacade).updateString(anyString(),anyString(),anyInt(),eq("test"));
        }
    }

    @Test
    void updateStatus() {
        doCallRealMethod().when(mockApp).updateStatus();
        when(mockApp.getTodoID(any())).thenReturn(1);
        when(mockApp.choseStatus()).thenReturn(1);
        mockApp.updateStatus();
        verify(mockDBFacade).updateInt(anyString(),anyString(),eq(1),eq(1));
    }

    @Test
    void choseStatus1() {
        when(mockApp.choseStatus()).thenReturn(1);
        assertTrue(mockApp.choseStatus() == 1);
    }
    @Test
    void choseStatus2() {
        when(mockApp.choseStatus()).thenReturn(2);
        assertTrue(mockApp.choseStatus() == 2);
    }

    @Test
    void updateAssignedUser() {
        doCallRealMethod().when(mockApp).updateAssignedUser();
        when(mockApp.getTodoID(any())).thenReturn(1);
        when(mockApp.getUserID(any())).thenReturn(1);
        mockApp.updateAssignedUser();
        verify(mockDBFacade).updateInt(anyString(),anyString(),eq(1),eq(1));
    }

    @Test
    void updateUserChoice1() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.updateUserChoice();
            verify(mockApp).updateName();
        }
    }
    @Test
    void updateUserChoice2() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(2);
            mockApp.updateUserChoice();
            verify(mockApp).updateAge();
        }
    }
    @Test
    void updateUserChoice3() {
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(3);
            boolean run = mockApp.updateUserChoice();
            assertFalse(run);
        }
    }
    @Test
    void updateUserChoiceInvalid() {
        String expected = "Wrong Input. Please Enter Again.\r\n";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(999);
            mockApp.updateUserChoice();
            assertEquals(expected, outputStream.toString());
        }
    }

    @Test
    void updateName() {
        doCallRealMethod().when(mockApp).updateName();
        when(mockApp.getUserID(any())).thenReturn(1);
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::Str).thenReturn("test");
            mockApp.updateName();
            verify(mockDBFacade).updateString(anyString(),anyString(),eq(1),eq("test"));
        }
    }

    @Test
    void updateAge() {
        doCallRealMethod().when(mockApp).updateAge();
        when(mockApp.getUserID(any())).thenReturn(1);
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            mockApp.updateAge();
            verify(mockDBFacade).updateInt(anyString(),anyString(),eq(1),eq(1));
        }
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
        doCallRealMethod().when(mockApp).deleteTodo();
        when(mockApp.getTodoID(any())).thenReturn(1);
        mockApp.deleteTodo();
        verify(mockDBFacade).deleteData(anyString(),eq(1));
    }

    @Test
    void deleteUser() {
        doCallRealMethod().when(mockApp).deleteUser();
        when(mockApp.getUserID(any())).thenReturn(1);
        mockApp.deleteUser();
        verify(mockDBFacade).deleteData(anyString(),eq(1));
    }

    @Test
    void getTodoID() {
        Map<Integer, Todo> todoMap = new HashMap<>();
        Todo todoTest = new Todo(1, "Homework", 3, "DONE");
        Todo todoTest2 = new Todo(2, "Homework2", 3, "DONE");
        todoMap.put(1, todoTest);
        todoMap.put(2, todoTest2);

        doCallRealMethod().when(mockApp).getTodoID(any());
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            int todoId = mockApp.getTodoID(todoMap);
            assertEquals(1,todoId);
            input.when(Input::number).thenReturn(2);
            todoId = mockApp.getTodoID(todoMap);
            assertEquals(2,todoId);
        }
    }

    @Test
    void getUserID() {
        Map<Integer, User> userMap = new HashMap<>();
        User user =new User(1, "Hugo", 5);
        User user2 =new User(2, "Toma", 5);
        userMap.put(1, user);
        userMap.put(2, user2);

        doCallRealMethod().when(mockApp).getUserID(any());
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(1);
            int todoId = mockApp.getUserID(userMap);
            assertEquals(1,todoId);
            input.when(Input::number).thenReturn(2);
            todoId = mockApp.getUserID(userMap);
            assertEquals(2,todoId);
        }
    }
}