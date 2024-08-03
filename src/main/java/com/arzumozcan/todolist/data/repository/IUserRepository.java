package com.arzumozcan.todolist.data.repository;

import com.arzumozcan.todolist.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    // Custom query method to find user by username
    Optional<User> findByUsername(String username);

    // Custom query method to find user by email
    Optional<User> findByEmail(String email);

    // Additional custom queries can be added here
}
