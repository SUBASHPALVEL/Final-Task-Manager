// UserService.java
package com.project.taskmanagement.service;

import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.dto.TaskDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long userId);

    List<TaskDTO> getAssignedTasksByUserId(Long userId);

    UserDTO login(String email, String password);
}
