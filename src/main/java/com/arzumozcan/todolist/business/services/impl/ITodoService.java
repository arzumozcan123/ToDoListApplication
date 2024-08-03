package com.arzumozcan.todolist.business.services.impl;

import java.util.List;

public interface ITodoService<D, E> {

    // Model Mapper
    D entityToDto(E e);

    E dtoToEntity(D d);

    // CRUD Operations
    // Create
    D createTodo(D d);

    List<D> getAllTodos();

    D getTodoById(Long id);

    D updateTodo(Long id, D d);

    D deleteTodoById(Long id);
}
