package com.arzumozcan.todolist.todo;

import com.arzumozcan.todolist.data.entity.Todo;
import com.arzumozcan.todolist.data.entity.User;
import com.arzumozcan.todolist.data.repository.IToDoRepository;
import com.arzumozcan.todolist.data.repository.IUserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

// LOMBOK
@Log4j2

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ToDoServicesImplTest {

    private Todo todo;
    private User user;

    private final IToDoRepository todoRepository;
    private final IUserRepository userRepository;

    @Autowired
    public ToDoServicesImplTest(IToDoRepository todoRepository, IUserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @BeforeAll
    static void setUpBeforeClass() {
        log.info("****** Todo Tests Start ******");
    }

    @BeforeEach
    void setUp() {
        log.info("Setting up test data...");
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user = userRepository.save(user);

        todo = new Todo();
        todo.setTaskName("Test Task");
        todo.setDetails("Test Details");
        todo.setCompleted(false);
        todo.setUser(user);
    }

    @AfterEach
    void tearDown() {
        log.info("Cleaning up test data...");
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    static void tearDownAfterClass() {
        log.info("****** Todo Tests End ******");
    }

    @Test
    @Order(1)
    public void testCreateTodo() {
        log.info("Creating a new Todo...");
        Todo savedTodo = todoRepository.save(todo);
        assertNotNull(savedTodo.getId(), "Todo ID should not be null after saving");
        assertEquals("Test Task", savedTodo.getTaskName());
        assertEquals("Test Details", savedTodo.getDetails());
    }

    @Test
    @Order(2)
    public void testFindTodo() {
        log.info("Finding a Todo...");
        todo = todoRepository.save(todo);
        Optional<Todo> foundTodo = todoRepository.findById(todo.getId());
        assertTrue(foundTodo.isPresent(), "Todo should be present");
        assertEquals("Test Task", foundTodo.get().getTaskName());
    }

    @Test
    @Order(3)
    public void testUpdateTodo() {
        log.info("Updating a Todo...");
        todo = todoRepository.save(todo);
        todo.setTaskName("Updated Task");
        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        assertEquals("Updated Task", updatedTodo.getTaskName());
        assertTrue(updatedTodo.isCompleted());
    }

    @Test
    @Order(4)
    public void testDeleteTodo() {
        log.info("Deleting a Todo...");
        todo = todoRepository.save(todo);
        todoRepository.deleteById(todo.getId());
        Optional<Todo> deletedTodo = todoRepository.findById(todo.getId());
        assertFalse(deletedTodo.isPresent(), "Todo should be deleted");
    }

    @Test
    @Order(5)
    public void testListTodos() {
        log.info("Listing all Todos...");
        todoRepository.save(todo);
        Iterable<Todo> todos = todoRepository.findAll();
        assertThat(todos).size().isGreaterThan(0);
    }
}
