package com.arzumozcan.todolist.todo;

import com.arzumozcan.todolist.data.entity.User;
import com.arzumozcan.todolist.data.repository.IUserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserServicesImplTest {

    private User user;

    private final IUserRepository userRepository;

    @Autowired
    public UserServicesImplTest(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeAll
    static void setUpBeforeClass() {
        log.info("****** User Tests Start ******");
    }

    @BeforeEach
    void setUp() {
        log.info("Setting up test data...");
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
    }

    @AfterEach
    void tearDown() {
        log.info("Cleaning up test data...");
        userRepository.deleteAll();
    }

    @AfterAll
    static void tearDownAfterClass() {
        log.info("****** User Tests End ******");
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        log.info("Creating a new User...");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId(), "User ID should not be null after saving");
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    @Order(2)
    public void testFindUser() {
        log.info("Finding a User...");
        user = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent(), "User should be present");
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        log.info("Updating a User...");
        user = userRepository.save(user);
        user.setUsername("updateduser");
        user.setEmail("updated@example.com");
        User updatedUser = userRepository.save(user);
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    @Order(4)
    public void testDeleteUser() {
        log.info("Deleting a User...");
        user = userRepository.save(user);
        userRepository.deleteById(user.getId());
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent(), "User should be deleted");
    }

    @Test
    @Order(5)
    public void testListUsers() {
        log.info("Listing all Users...");
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        assertThat(users).size().isGreaterThan(0);
    }
}
