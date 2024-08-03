package com.arzumozcan.todolist.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueTodoTaskNameValidation.class })
public @interface AUniqueTodoTaskName {
    String message() default "Todo task name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
