package com.example.rest.mapper;

import com.example.rest.dto.UserDto;
import com.example.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private TaskMapper taskMapper;

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setTaskList(
                user.getTaskList()
                        .stream()
                        .map(taskMapper::toDto)
                        .collect(Collectors.toList())
        );
        return userDto;
    }
}
