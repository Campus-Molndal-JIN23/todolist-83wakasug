package org.campusmolndal;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


class InputTest {

    @Test
    void testStr() {
        String expected = "test";
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::Str).thenReturn(expected);
            String actual = Input.Str();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testNumber() {
        int expected = 5;
        try (MockedStatic<Input> input = Mockito.mockStatic(Input.class)) {
            input.when(Input::number).thenReturn(expected);
            int actual = Input.number();
            assertEquals(expected, actual);
        }
    }
}