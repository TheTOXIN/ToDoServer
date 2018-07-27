package com.toxin.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(
    name = "tasks",
    indexes = @Index(columnList = "title, user_id", name = "tasks_title_user_idx"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"title", "user_id"}, name = "tasks_title_user_unique")
)
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;
    
    @Column(nullable = false)
    private Boolean finished;

    @Column(nullable = false)
    private LocalDate date;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

}
