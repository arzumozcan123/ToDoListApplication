package com.arzumozcan.todolist.business.services;

import com.arzumozcan.todolist.business.dto.UserDto;
import com.arzumozcan.todolist.business.services.impl.IUserService;
import com.arzumozcan.todolist.data.entity.User;
import com.arzumozcan.todolist.data.entity.Todo;
import com.arzumozcan.todolist.data.repository.IUserRepository;
import com.arzumozcan.todolist.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements IUserService<UserDto, User> {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto entityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        return entityToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return entityToDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        // Update user fields
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        // Convert TodoDto to Todo and set todos
        Set<Todo> todos = userDto.getTodos().stream()
                .map(todoDto -> modelMapper.map(todoDto, Todo.class))
                .collect(Collectors.toSet());
        existingUser.setTodos(todos);

        User updatedUser = userRepository.save(existingUser);
        return entityToDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));
        return entityToDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        return entityToDto(user);
    }
}
