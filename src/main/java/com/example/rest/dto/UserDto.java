package com.example.rest.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private List<TaskDto> taskList;

    public List<TaskDto> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDto> taskList) {
        this.taskList = taskList;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
