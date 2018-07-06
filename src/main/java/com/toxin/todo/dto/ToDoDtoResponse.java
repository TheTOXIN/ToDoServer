package com.toxin.todo.dto;

import com.toxin.todo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDtoResponse implements Serializable {
    private List<Task> tasks;
    private String welcome;
}
