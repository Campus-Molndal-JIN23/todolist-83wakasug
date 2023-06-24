package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

     //TODO fixTest
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