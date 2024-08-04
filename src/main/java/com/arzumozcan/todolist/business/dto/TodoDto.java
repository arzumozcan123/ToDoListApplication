package com.arzumozcan.todolist.business.dto;
import com.arzumozcan.todolist.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation
public class TodoDto extends AuditingAwareBaseDto implements Serializable {

    // Serialization
    public static final Long serialVersionUID = 1L;

    // ID
    private Long id;

    // Date
    @Builder.Default
    private Date createdDate = new Date(System.currentTimeMillis());

    // Task Name
    @NotEmpty(message = "{todo.taskName.validation.constraints.NotNull.message}")
    @Size(min = 5, message = "{todo.taskName.least.validation.constraints.NotNull.message}")
    private String taskName;

    // Completed
    private boolean completed;

    // Details
    @Size(max = 500, message = "{todo.details.max.validation.constraints.NotNull.message}")
    private String details;
}
