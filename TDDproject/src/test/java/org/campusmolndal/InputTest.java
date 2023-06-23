package org.campusmolndal;

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

    @BeforeEach
    void setUp() {
        // Set up the InputStream with test input
        String testInput = "Katt";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
    }

    @Test
    void testStr() {
        String expected = "Katt";
        String actual = Input.Str();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 0, -100})
    void testNumber(int inputNumber) {
        int expected = inputNumber;
        int actual = Input.number();
        assertEquals(expected, actual);
    }
    
}