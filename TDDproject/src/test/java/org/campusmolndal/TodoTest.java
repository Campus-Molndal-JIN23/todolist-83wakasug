package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo(5, "GoShipping", 12, "1");
    }

    @Test
    void testGetId() {
        assertEquals(5, todo.getId());
    }

    @Test
    void testSetId() {
        todo.setId(12);
        assertEquals(12, todo.getId());
    }

    @Test
    void testGetText() {
        assertEquals("GoShipping", todo.getText());
    }

    @Test
    void testSetText() {
        todo.setText("GoShipping");
        assertEquals("GoShipping", todo.getText());
    }

    @Test
    void testGetDone() {
        assertEquals("1", todo.getDone());
    }

    @Test
    void testSetDone() {
        todo.setDone("2");
        assertEquals("2", todo.getDone());
    }

    @Test
    void testGetAssignedTo() {
        assertEquals(12, todo.getAssignedTo());
    }

    @Test
    void testSetAssignedTo() {
        todo.setAssignedTO(456);
        assertEquals(456, todo.getAssignedTo());
    }

    @Test
    void testGetProgress() {
        assertEquals("TODO", todo.getProgress());
    }

    @Test
    void testToString() {
        String expected = "\nTODO: GoShipping\nProgress: TODO";
        assertEquals(expected, todo.toString());
    }
}