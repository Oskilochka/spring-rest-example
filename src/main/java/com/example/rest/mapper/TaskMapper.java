package com.example.rest.mapper;

import com.example.rest.dto.TaskDto;
import com.example.rest.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.getCompleted());
        return taskDto;
    }
}
