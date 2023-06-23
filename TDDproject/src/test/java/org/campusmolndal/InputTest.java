package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InputTest {
Scanner mockScanner;

@BeforeEach
void setup(){
    mockScanner = Mockito.mock(Scanner.class);
    when(mockScanner.nextLine()).thenReturn(Mockito.anyString());
    when(mockScanner.nextInt()).thenReturn(Mockito.anyInt());

}


    @ParameterizedTest
    @ValueSource(strings = {"Katt ","Hund","1","Toma starts eating fish."})
    void CheckIfReturnValueISCorrect(String value) {
        when(mockScanner.nextLine()).thenReturn(value);
        String expected =value;
        String actual= Input.Str();
        assertEquals(expected,actual);
    }
    @ParameterizedTest
    @CsvSource( {"1,1","2,2","3,3","0,0","100,100"})
    void checkIfItreturnsInt(int value,String value2) {
        int expected = value;
        when(mockScanner.nextLine()).thenReturn(String.valueOf(Mockito.anyInt()));
        when(mockScanner.nextLine()).thenReturn(value2);
        int actual = Integer.parseInt(mockScanner.nextLine());
        assertEquals(expected,actual);
    }
    @ParameterizedTest
    @ValueSource( strings = {"Katt ","Hund","Toma starts eating fish."})
    void CheckErrorMessageComingUp(String value) {
        when(mockScanner.nextLine()).thenReturn(value);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        assertEquals("Wrong input, Try Again", outputStream.toString().trim());

    }
}