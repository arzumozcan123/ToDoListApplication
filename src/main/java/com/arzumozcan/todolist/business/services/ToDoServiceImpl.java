package com.arzumozcan.todolist.business.services;

import com.arzumozcan.todolist.business.dto.TodoDto;
import com.arzumozcan.todolist.data.entity.Todo;
import com.arzumozcan.todolist.data.repository.IToDoRepository;
import com.arzumozcan.todolist.business.services.impl.ITodoService;
import com.arzumozcan.todolist.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class ToDoServiceImpl implements ITodoService<TodoDto, Todo> {

    private final IToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public TodoDto entityToDto(Todo todo) {
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public Todo dtoToEntity(TodoDto todoDto) {
        return modelMapper.map(todoDto, Todo.class);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CREATE
    @Override
    @Transactional
    public TodoDto createTodo(TodoDto todoDto) {
        if (todoDto != null) {
            Todo todo = dtoToEntity(todoDto);
            Todo savedTodo = toDoRepository.save(todo);
            todoDto.setId(savedTodo.getId());
            todoDto.setCreatedDate(savedTodo.getCreatedDate());
        } else {
            throw new NullPointerException("TodoDto cannot be null");
        }
        return todoDto;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIST
    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = toDoRepository.findAll();
        List<TodoDto> todoDtoList = todos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        log.info("List size: " + todoDtoList.size());
        return todoDtoList;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FIND
    @Override
    public TodoDto getTodoById(Long id) {
        if (id == null) {
            throw new NullPointerException("ID cannot be null");
        }
        Todo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
        return entityToDto(todo);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UPDATE
    @Override
    @Transactional
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo existingTodo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));

        if (todoDto != null) {
            existingTodo.setTaskName(todoDto.getTaskName());
            existingTodo.setDetails(todoDto.getDetails());
            existingTodo.setCompleted(todoDto.isCompleted());

            Todo updatedTodo = toDoRepository.save(existingTodo);
            return entityToDto(updatedTodo);
        } else {
            throw new NullPointerException("TodoDto cannot be null");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DELETE
    @Override
    @Transactional
    public TodoDto deleteTodoById(Long id) {
        Todo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
        toDoRepository.delete(todo);
        return entityToDto(todo);
    }
}
