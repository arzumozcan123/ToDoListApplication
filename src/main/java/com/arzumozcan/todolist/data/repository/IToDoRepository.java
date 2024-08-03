package com.arzumozcan.todolist.data.repository;

import com.arzumozcan.todolist.data.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IToDoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTaskName(String taskName);

}
