package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

class DBCRUDTest {
PreparedStatement mockpstm;

    @BeforeEach
    void setUp() {
        Mockito.framework().clearInlineMocks();
        mockpstm = Mockito.mock(PreparedStatement.class);
    }


    @ParameterizedTest
    @CsvSource ({ "INSERT INTO TODO (DESCRIPTION, PROGRESS,ASSIGNEDTO),Wasabi,1","\"INSERT INTO TODO (DESCRIPTION, PROGRESS,ASSIGNEDTO),GoShopping,5"

    })
    void addData(String Value,String value2,int value3) {


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