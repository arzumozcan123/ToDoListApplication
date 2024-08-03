package com.arzumozcan.todolist.annotation;

import com.arzumozcan.todolist.data.repository.IToDoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

// LOMBOK
@RequiredArgsConstructor
public class UniqueTodoTaskNameValidation implements ConstraintValidator<AUniqueTodoTaskName, String> {

    private final IToDoRepository todoRepository;

    @Override
    public boolean isValid(String taskName, ConstraintValidatorContext context) {
        // Check if the Todo task name already exists in the database
        boolean isSameTaskName = todoRepository.findByTaskName(taskName).isPresent();
        if (isSameTaskName) {
            System.out.println(taskName + " already exists in the database. Please choose a different task name.");
            return false;
        } else {
            System.out.println(taskName + " is unique and can be used.");
            return true;
        }
    }
}
