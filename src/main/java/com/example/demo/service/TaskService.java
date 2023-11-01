package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.model.TaskModel;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public TaskModel createTask(Task task, Long userId) {
        User user = userRepository.findById(userId).get();
        task.setUser(user);
        return TaskModel.toModel(taskRepository.save(task));
    }

    public TaskModel updateTask(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        task.setCompleted(!task.getCompleted());
        return TaskModel.toModel(taskRepository.save(task));
    }

    public List<Task> getAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task getTask(Long id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id).get();
        if (task == null) {
            throw new TaskNotFoundException("Task was not found");
        }
        return task;
    }

    public Long deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
        return taskId;
    }
}
