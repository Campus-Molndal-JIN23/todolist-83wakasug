package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DBCRUDTest {
PreparedStatement mockpstm;

    @BeforeEach
    void setUp() {
        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
    }


    @Test
    void addData() throws SQLException {
       String value = "DESCRIPTION";
       int value2 = 1;
        mockpstm.setString(1, value);
        mockpstm.setInt(2, value2);


        verify(mockpstm, times(1)).setString(1, value);
        verify(mockpstm, times(1)).setInt(2, value2);
    }

    @Test
    void showALLTodo() {
    }

    @Test
    void showTodo() {
    }

    @Test
    void showONETodo() {
    }

    @Test
    void showSingleUser() {
    }

    @Test
    void showUsers() {
    }

    @Test
    void showALLUser() {
    }

    @Test
    void updateDataInt() {
    }

    @Test
    void updateDataString() {
    }

    @Test
    void deleteData() {
    }
}