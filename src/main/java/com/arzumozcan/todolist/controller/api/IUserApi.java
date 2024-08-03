package com.arzumozcan.todolist.controller.api;
import com.arzumozcan.todolist.business.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
public interface IUserApi {

    @PostMapping("/create")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @GetMapping("/all")
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    @PutMapping("/update/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id);

    @GetMapping("/findByUsername/{username}")
    ResponseEntity<UserDto> findByUsername(@PathVariable String username);

    @GetMapping("/findByEmail/{email}")
    ResponseEntity<UserDto> findByEmail(@PathVariable String email);
}
