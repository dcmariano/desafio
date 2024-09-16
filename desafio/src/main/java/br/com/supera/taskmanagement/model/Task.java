package br.com.supera.taskmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Boolean isHighlighted;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
}
