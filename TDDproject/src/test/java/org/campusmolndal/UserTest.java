package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "Wasabi", 12);
    }

    @Test
    void testGetId() {
        assertEquals(1, user.getId());
    }

    @Test
    void testSetId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Wasabi", user.getName());
    }

    @Test
    void testSetName() {
        user.setName("Toma");
        assertEquals("Toma", user.getName());
    }

    @Test
    void testGetAge() {
        assertEquals(12, user.getAge());
    }

    @Test
    void testSetAge() {
        user.setAge(20);
        assertEquals(20, user.getAge());
    }

    @Test
    void testGetTodos() {
        assertNull(user.getTodos());
    }

    @Test
    void testSetTodos() {
        ArrayList<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "Go Shopping", 123, "1"));
        todos.add(new Todo(2, "Runing", 500, "150"));
        user.setTodos(todos);
        assertEquals(todos, user.getTodos());
    }

    @Test
    void testAddTodo() {
        ArrayList<Todo> todos = new ArrayList<>();
        Todo todo = new Todo(1, "Cleaning", 50, "2");
        todos.add(todo);
        user.setTodos(todos);

        Todo newTodo = new Todo(2, "homework", 30, "1");
        user.addTodo(newTodo);

        assertTrue(user.getTodos().contains(todo));
        assertTrue(user.getTodos().contains(newTodo));
    }

    @Test
    void testToString() {
        String expected = "\nAssigned to: Wasabi\nAge: 12";
        assertEquals(expected, user.toString());
    }
}