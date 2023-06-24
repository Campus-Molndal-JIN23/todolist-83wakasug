package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class InputTest {
    private InputStream inputStream;
    //Reference https://stackoverflow.com/questions/76239380/how-to-take-predefined-user-input-for-unit-tests-in-java

    @AfterEach
    void setUp() {
        System.setIn(System.in);
    }


    @Test
    void testStr() {
        String testInput = "Katt";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        String expected = "Katt";
        String actual = Input.Str();
        assertEquals(expected, actual);
    }

     //TODO fixTest
   @Test
    void testNumber() {
       String number = String.valueOf(1);  // Convert int to String
       ByteArrayInputStream inputStream = new ByteArrayInputStream(number.getBytes());
       System.setIn(inputStream);
       int expected = Integer.parseInt(number);
       int actual = Input.number();
       assertEquals(expected, actual);
    }

}