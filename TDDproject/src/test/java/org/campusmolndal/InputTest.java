package org.campusmolndal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class InputTest {

    //Reference https://stackoverflow.com/questions/76239380/how-to-take-predefined-user-input-for-unit-tests-in-java

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
    void testStr() throws IOException {
        InputStream inputStream;
        String testInput = "Katt";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        String expected = "Katt";
        String actual = Input.Str();
        inputStream.close();
        assertEquals(expected, actual);

    }


   @Test
    void testNumber() throws IOException {
      InputStream inputStream;
       String number = String.valueOf(1);  // Convert int to String
       inputStream = new ByteArrayInputStream(number.getBytes());
       System.setIn(inputStream);
       int expected = Integer.parseInt(number);
       int actual = Input.number();
       inputStream.close();
       assertEquals(expected, actual);
    }

}