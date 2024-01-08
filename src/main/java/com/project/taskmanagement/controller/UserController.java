package com.project.taskmanagement.controller;

import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.exception.BusinessException;
import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateUser(userDTO);
        if (updatedUserDTO != null) {
            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping("/{userId}/tasks")
    // public ResponseEntity<List<TaskDTO>> getAssignedTasksByUserId(@PathVariable Long userId) {
    //     List<TaskDTO> assignedTasks = userService.getAssignedTasksByUserId(userId);
    //     if (assignedTasks != null) {
    //         return new ResponseEntity<>(assignedTasks, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    
    @GetMapping("/{userId}/tasks")
    public String getAssignedTasksByUserId(@PathVariable Long userId, Model model) {
        List<TaskDTO> assignedTasks = userService.getAssignedTasksByUserId(userId);
        model.addAttribute("tasks", assignedTasks);
        return "userTasks";
    }
    
    @PostMapping(path = "/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.login(userDTO.getUsermail(), userDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // @PostMapping(path = "/login",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    // public String login(@Valid @ModelAttribute("user") UserDTO userDTO) {
    //     UserDTO loggedInUser = userService.login(userDTO.getUsermail(), userDTO.getPassword());

    // if (loggedInUser != null) {
    //     // Login successful
    //     return ("success");
    // } else {
    //     // Login failed
    //     return ("failure");
    // }
    // }

//     @PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
// public String login(@Valid @ModelAttribute("user") UserDTO userDTO, Model model) {
//     try {
//         UserDTO loggedInUser = userService.login(userDTO.getUsermail(), userDTO.getPassword());
//         // Add the loggedInUser to the model if needed in the HTML template
//         model.addAttribute("loggedInUser", loggedInUser);
//         return "success"; // Assuming "success" is the name of your Thymeleaf template
//     } catch (BusinessException ex) {
//         // Handle BusinessException by adding error details to the model
//         model.addAttribute("errorList", ex.getErrorList());
//         return "failure"; // Assuming "failure" is the name of your Thymeleaf template
//     }
// }

}
