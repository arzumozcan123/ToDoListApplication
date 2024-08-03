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
import java.util.Set;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

// ENTITY
@Entity(name = "Users") // Sql JOIN için yazdım
@Table(name = "user")

// User(1) ToDo(N)
public class User extends AuditingAwareBaseEntity implements Serializable {

    // SERILEŞTIRME
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // USERNAME
    @Column(name = "username", nullable = false)
    private String username;

    // PASSWORD
    @Column(name = "password", nullable = false)
    private String password;

    // EMAIL
    @Column(name = "email", nullable = false)
    private String email;

    // RELATION
    // User(1) ToDo(N)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Todo> todos;
}

