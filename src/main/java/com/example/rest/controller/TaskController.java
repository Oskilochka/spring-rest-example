package com.example.rest.controller;

import com.example.rest.entity.Task;
import com.example.rest.exceptions.NotFoundException;
import com.example.rest.service.TaskService;
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
            return ResponseEntity.ok(taskService.getById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(taskService.create(task, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestParam Long taskId) {
        try {
            return ResponseEntity.ok(taskService.update(taskId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.deleteById(id) ? "Task was deleted" : "Task was not deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
}
