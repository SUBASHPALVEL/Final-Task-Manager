// TaskService.java
package com.project.taskmanagement.service;

import com.project.taskmanagement.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO getTaskById(Long taskId);

    List<TaskDTO> getAllTasks();

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO taskDTO);

    void deleteTask(Long taskId);

    // Methods for updating specific fields
    TaskDTO updateTaskTitle(Long taskId, String title);

    TaskDTO updateTaskDescription(Long taskId, String description);

    TaskDTO updateTaskStatus(Long taskId, String status);

    TaskDTO updateTaskPriority(Long taskId, String priority);

    TaskDTO updateTaskDueDate(Long taskId, String dueDate);

    TaskDTO updateTaskAssignedUsers(Long taskId, List<Long> assignedUserIds);

    // Methods for retrieving specific fields
    String getTaskTitle(Long taskId);

    String getTaskDescription(Long taskId);

    String getTaskStatus(Long taskId);

    String getTaskPriority(Long taskId);

    String getTaskDueDate(Long taskId);

    List<String> getTaskAssignedUsers(Long taskId);

    List<TaskDTO> getTasksByUserId(Long userId);
}
