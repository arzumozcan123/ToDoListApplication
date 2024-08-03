package com.arzumozcan.todolist.controller.api.impl;

import com.arzumozcan.todolist.business.dto.UserDto;
import com.arzumozcan.todolist.business.services.UserServiceImpl;
import com.arzumozcan.todolist.controller.api.IUserApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/users")
public class UserApiImpl implements IUserApi {

    private final UserServiceImpl userService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }

    @Override
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        UserDto user = userService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        UserDto user = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
