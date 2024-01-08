package com.project.taskmanagement.converter;

import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.entity.UserEntity;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        // userDTO.setAssignedTasks(userEntity.getAssignedTasks().stream()
        //         .map(TaskConverter::convertToDTO)
        //         .collect(Collectors.toList()));
        return userDTO;
    }

    public static UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setUsermail(userDTO.getUsermail());
        userEntity.setPassword(userDTO.getPassword());
        // Note: Do not set assignedTasks in the opposite direction to avoid cyclic references
        return userEntity;
    }
}

