package com.example.rest.service;

import com.example.rest.entity.Task;
import com.example.rest.entity.User;
import com.example.rest.dto.TaskDto;
import com.example.rest.exceptions.NotFoundException;
import com.example.rest.mapper.TaskMapper;
import com.example.rest.repository.TaskRepository;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public TaskDto getById(Long id) throws NotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new NotFoundException("Task was not found");
        }
        return taskMapper.toDto(task.get());
    }

    public TaskDto create(Task task, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(task::setUser);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto update(Long taskId) throws NotFoundException {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            task.get().setCompleted(!task.get().getCompleted());
            return taskMapper.toDto(taskRepository.save(task.get()));
        } else {
            throw new NotFoundException("Tasks doesn't exist");
        }
    }

    public boolean deleteById(Long taskId) {
        if (taskRepository.findById(taskId).isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        } else {
            return false;
        }
    }
}
