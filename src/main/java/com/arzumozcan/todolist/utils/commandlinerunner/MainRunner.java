package com.arzumozcan.todolist.utils.commandlinerunner;

import com.arzumozcan.todolist.data.entity.Todo;
import com.arzumozcan.todolist.data.entity.User;
import com.arzumozcan.todolist.data.repository.IToDoRepository;
import com.arzumozcan.todolist.data.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

// LOMBOK
@RequiredArgsConstructor

// Command Line Runner
@Configuration
@Log4j2
public class MainRunner {

    // INJECTION
    private final IUserRepository userRepository;
    private final IToDoRepository todoRepository;

    // START
    public void start() {
        log.info("### START #######");
    }

    // Command Line Runner
    @Bean
    public CommandLineRunner initializeData() {
        // Lambda Expression
        return args -> {
            log.info("Creating sample users and todos");

            // Sample User 1
            User user1 = User.builder()
                    .username("user1")
                    .password("password1")
                    .email("user1@example.com")
                    .todos(new HashSet<>())
                    .build();
            userRepository.save(user1);

            // Sample User 2
            User user2 = User.builder()
                    .username("user2")
                    .password("password2")
                    .email("user2@example.com")
                    .todos(new HashSet<>())
                    .build();
            userRepository.save(user2);

            // Sample Todo 1 for User 1
            Todo todo1 = Todo.builder()
                    .taskName("Task 1 for User 1")
                    .details("Details for Task 1")
                    .completed(false)
                    .user(user1)
                    .build();
            todoRepository.save(todo1);
            user1.getTodos().add(todo1);
            userRepository.save(user1);

            // Sample Todo 2 for User 1
            Todo todo2 = Todo.builder()
                    .taskName("Task 2 for User 1")
                    .details("Details for Task 2")
                    .completed(false)
                    .user(user1)
                    .build();
            todoRepository.save(todo2);
            user1.getTodos().add(todo2);
            userRepository.save(user1);

            // Sample Todo 1 for User 2
            Todo todo3 = Todo.builder()
                    .taskName("Task 1 for User 2")
                    .details("Details for Task 1 for User 2")
                    .completed(false)
                    .user(user2)
                    .build();
            todoRepository.save(todo3);
            user2.getTodos().add(todo3);
            userRepository.save(user2);

            log.info("Sample users and todos created successfully");
        };
    }

    // END
    public void end() {
        log.info("### END #######");
    }
}
