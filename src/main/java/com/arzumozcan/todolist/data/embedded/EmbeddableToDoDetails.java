package com.arzumozcan.todolist.data.embedded;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

@Embeddable
public class EmbeddableToDoDetails {

    // DESCRIPTION
    @Column(name = "description", columnDefinition = "varchar(255) default 'No description provided'")
    private String description;

    // PRIORITY
    @Column(name = "priority", nullable = false, columnDefinition = "int default 1")
    private int priority;

    // NOTES
    @Lob
    @Column(name = "notes", columnDefinition = "varchar(255) default 'No additional notes'")
    private String notes;

    // SPECIAL DATA
    @Transient
    private Object specialData;
}
