package com.arzumozcan.todolist.business.services.impl;

import java.util.List;

public interface IUserService<D, E> {

    // Model Mapper
    D entityToDto(E e);

    E dtoToEntity(D d);

    // CRUD Operations
    D createUser(D d);

    List<D> getAllUsers();

    D getUserById(Long id);

    D updateUser(Long id, D d);

    void deleteUser(Long id);

    // Additional Methods
    D findByUsername(String username);

    D findByEmail(String email);
}
