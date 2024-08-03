package com.arzumozcan.todolist.controller.api;

import com.arzumozcan.todolist.business.dto.TodoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todos")
public interface IToDoApi {

    @PostMapping
    ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto);

    @GetMapping
    ResponseEntity<List<TodoDto>> getAllTodos();

    @GetMapping("/{id}")
    ResponseEntity<TodoDto> getTodoById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto);

    @DeleteMapping("/{id}")
    ResponseEntity<TodoDto> deleteTodoById(@PathVariable Long id);

    @DeleteMapping("/delete/all")
    ResponseEntity<Void> deleteAllTodos();
}
