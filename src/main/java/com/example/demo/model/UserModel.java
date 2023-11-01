package com.example.demo.model;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserModel {
    private Long id;
    private String username;
    private List<TaskModel> taskList;

    public static UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setTaskList(
                user.getTaskList()
                        .stream()
                        .map(TaskModel::toModel)
                        .collect(Collectors.toList())
        );
        return userModel;
    }

    public List<TaskModel> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    public UserModel() {
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
