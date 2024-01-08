package com.project.taskmanagement.controller;

import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        TaskDTO taskDTO = taskService.getTaskById(taskId);
        if (taskDTO != null) {
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOList = taskService.getAllTasks();
        return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTaskDTO = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTaskDTO = taskService.updateTask(taskDTO);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Additional Endpoints for Updating Specific Fields

    @PatchMapping("/{taskId}/title")
    public ResponseEntity<TaskDTO> updateTaskTitle(@PathVariable Long taskId, @RequestParam String title) {
        TaskDTO updatedTaskDTO = taskService.updateTaskTitle(taskId, title);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}/description")
    public ResponseEntity<TaskDTO> updateTaskDescription(@PathVariable Long taskId, @RequestParam String description) {
        TaskDTO updatedTaskDTO = taskService.updateTaskDescription(taskId, description);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        TaskDTO updatedTaskDTO = taskService.updateTaskStatus(taskId, status);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}/priority")
    public ResponseEntity<TaskDTO> updateTaskPriority(@PathVariable Long taskId, @RequestParam String priority) {
        TaskDTO updatedTaskDTO = taskService.updateTaskPriority(taskId, priority);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}/dueDate")
    public ResponseEntity<TaskDTO> updateTaskDueDate(@PathVariable Long taskId, @RequestParam String dueDate) {
        TaskDTO updatedTaskDTO = taskService.updateTaskDueDate(taskId, dueDate);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}/assignedUsers")
    public ResponseEntity<TaskDTO> updateTaskAssignedUsers(@PathVariable Long taskId, @RequestParam List<Long> assignedUserIds) {
        TaskDTO updatedTaskDTO = taskService.updateTaskAssignedUsers(taskId, assignedUserIds);
        if (updatedTaskDTO != null) {
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Additional Endpoints for Retrieving Specific Fields

    @GetMapping("/{taskId}/title")
    public ResponseEntity<String> getTaskTitle(@PathVariable Long taskId) {
        String taskTitle = taskService.getTaskTitle(taskId);
        if (taskTitle != null) {
            return new ResponseEntity<>(taskTitle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/description")
    public ResponseEntity<String> getTaskDescription(@PathVariable Long taskId) {
        String taskDescription = taskService.getTaskDescription(taskId);
        if (taskDescription != null) {
            return new ResponseEntity<>(taskDescription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/status")
    public ResponseEntity<String> getTaskStatus(@PathVariable Long taskId) {
        String taskStatus = taskService.getTaskStatus(taskId);
        if (taskStatus != null) {
            return new ResponseEntity<>(taskStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/priority")
    public ResponseEntity<String> getTaskPriority(@PathVariable Long taskId) {
        String taskPriority = taskService.getTaskPriority(taskId);
        if (taskPriority != null) {
            return new ResponseEntity<>(taskPriority, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/dueDate")
    public ResponseEntity<String> getTaskDueDate(@PathVariable Long taskId) {
        String taskDueDate = taskService.getTaskDueDate(taskId);
        if (taskDueDate != null) {
            return new ResponseEntity<>(taskDueDate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{taskId}/assignedUsers")
    public ResponseEntity<List<String>> getTaskAssignedUsers(@PathVariable Long taskId) {
        List<String> assignedUsers = taskService.getTaskAssignedUsers(taskId);
        if (assignedUsers != null) {
            return new ResponseEntity<>(assignedUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  @GetMapping("/userTasks/{userId}")
    // public String getTasksByUserId(@PathVariable Long userId, Model model) {
    //     List<TaskDTO> assignedtasks = taskService.getTasksByUserId(userId);
    //     model.addAttribute("tasks", assignedtasks);
    //     return "tasklist";
    // }

    @GetMapping("/userTasks/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long userId) {
        List<TaskDTO> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
