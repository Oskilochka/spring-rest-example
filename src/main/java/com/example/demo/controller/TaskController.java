package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity getTasks() {
        try {
            return ResponseEntity.ok(taskService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/single")
    public ResponseEntity getTask(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(taskService.getTask(id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(taskService.createTask(task, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestParam Long taskId) {
        try {
            return ResponseEntity.ok(taskService.updateTask(taskId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Task with id: " + taskService.deleteTask(id) + " was deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
}
