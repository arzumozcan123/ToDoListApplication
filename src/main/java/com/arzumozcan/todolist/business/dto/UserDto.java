package com.arzumozcan.todolist.business.dto;

import com.arzumozcan.todolist.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation
public class UserDto extends AuditingAwareBaseDto implements Serializable {

    // Serialization
    public static final Long serialVersionUID = 1L;

    // ID
    private Long id;

    // Date
    @Builder.Default
    private Date createdDate = new Date(System.currentTimeMillis());

    // Username
    @NotEmpty(message = "{user.username.validation.constraints.NotNull.message}")
    @Size(min = 5, message = "{user.username.least.validation.constraints.NotNull.message}")
    private String username;

    // Password
    @NotEmpty(message = "{user.password.validation.constraints.NotNull.message}")
    @Size(min = 8, message = "{user.password.least.validation.constraints.NotNull.message}")
    private String password;

    // Email
    @NotEmpty(message = "{user.email.validation.constraints.NotNull.message}")
    @Email(message = "{user.email.valid.validation.constraints.NotNull.message}")
    private String email;

    // Todos
    private Set<TodoDto> todos;
}
