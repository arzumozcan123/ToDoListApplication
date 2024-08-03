package com.arzumozcan.todolist.controller.api.impl;

import com.arzumozcan.todolist.controller.api.IToDoApi;
import com.arzumozcan.todolist.business.dto.TodoDto;
import com.arzumozcan.todolist.business.services.impl.ITodoService;
import com.arzumozcan.todolist.data.entity.Todo;
import com.arzumozcan.todolist.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoApiImpl implements IToDoApi {

    private final ITodoService<TodoDto, Todo> todoService;

    public ToDoApiImpl(ITodoService<TodoDto, Todo> todoService) {
        this.todoService = todoService;
    }

    @Override
    public ResponseEntity<TodoDto> createTodo(TodoDto todoDto) {
        TodoDto createdTodo = todoService.createTodo(todoDto);
        return ResponseEntity.ok(createdTodo);
    }

    @Override
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(Long id) {
        try {
            TodoDto todo = todoService.getTodoById(id);
            return ResponseEntity.ok(todo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TodoDto> updateTodo(Long id, TodoDto todoDto) {
        try {
            TodoDto updatedTodo = todoService.updateTodo(id, todoDto);
            return ResponseEntity.ok(updatedTodo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TodoDto> deleteTodoById(Long id) {
        try {
            TodoDto deletedTodo = todoService.deleteTodoById(id);
            return ResponseEntity.ok(deletedTodo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Void> deleteAllTodos() {
        // Logic to delete all todos
        return ResponseEntity.ok().build();
    }
}