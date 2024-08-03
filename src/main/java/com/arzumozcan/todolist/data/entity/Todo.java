package com.arzumozcan.todolist.data.entity;

import com.arzumozcan.todolist.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

// ENTITY
@Entity(name = "ToDos") // Sql JOIN için yazdım
@Table(name = "todo")

// Todo(N)  User(1)
public class Todo extends AuditingAwareBaseEntity implements Serializable {

    // SERILEŞTIRME
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // TASK NAME
    @Column(name = "task_name", nullable = false)
    private String taskName;

    // COMPLETED
    @Column(name = "completed")
    private boolean completed;

    // DETAILS
    @Column(name = "details", length = 500)
    private String details;

    // RELATION
    // Todo(N) User(1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Avoid using user in hashCode and equals to prevent StackOverflowError
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Todo todo = (Todo) obj;
        return id != null && id.equals(todo.id);
    }

}
