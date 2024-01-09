// TaskServiceImpl.java
package com.project.taskmanagement.service.impl;

import com.project.taskmanagement.converter.TaskConverter;
import com.project.taskmanagement.converter.UserConverter;
import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.dto.UserDTO;
import com.project.taskmanagement.entity.Priority;
import com.project.taskmanagement.entity.Status;
import com.project.taskmanagement.entity.TaskEntity;
import com.project.taskmanagement.entity.UserEntity;
import com.project.taskmanagement.repository.TaskRepository;
import com.project.taskmanagement.repository.UserRepository;
import com.project.taskmanagement.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public TaskDTO getTaskById(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null) ? TaskConverter.convertToDTO(taskEntity) : null;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        
        return taskEntities.stream()
                .map(taskEntity -> {
                   List<UserEntity> userEntities = taskEntity.getAssignedUsers();
                   List<UserDTO> dtoList = userEntities.stream()
                   .map(UserConverter::convertToDTO)
                   .collect(Collectors.toList());
                   TaskDTO convertToDTO = TaskConverter.convertToDTO(taskEntity);
                   convertToDTO.setAssignedUsers(dtoList);
return convertToDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = TaskConverter.convertToEntity(taskDTO);
        taskRepository.save(taskEntity);
        return TaskConverter.convertToDTO(taskEntity);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskDTO.getTaskId()).orElse(null);
        if (existingTaskEntity != null) {
            TaskEntity updatedTaskEntity = TaskConverter.convertToEntity(taskDTO);
            taskRepository.save(updatedTaskEntity);
            return TaskConverter.convertToDTO(updatedTaskEntity);
        }
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDTO updateTaskTitle(Long taskId, String title) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            existingTaskEntity.setTitle(title);
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public TaskDTO updateTaskDescription(Long taskId, String description) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            existingTaskEntity.setDescription(description);
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public TaskDTO updateTaskStatus(Long taskId, String status) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            // Assume 'status' is an enumeration, update accordingly
            existingTaskEntity.setStatus(Status.valueOf(status));
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public TaskDTO updateTaskPriority(Long taskId, String priority) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            // Assume 'priority' is an enumeration, update accordingly
            existingTaskEntity.setPriority(Priority.valueOf(priority));
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public TaskDTO updateTaskDueDate(Long taskId, String dueDate) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            // Assume 'dueDate' is a string representation of LocalDate, update accordingly
            existingTaskEntity.setDueDate(LocalDate.parse(dueDate));
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public TaskDTO updateTaskAssignedUsers(Long taskId, List<Long> assignedUserIds) {
        TaskEntity existingTaskEntity = taskRepository.findById(taskId).orElse(null);
        if (existingTaskEntity != null) {
            // Assuming you have a UserEntity repository to fetch UserEntities
            List<UserEntity> assignedUsers = userRepository.findAllById(assignedUserIds);
            existingTaskEntity.setAssignedUsers(assignedUsers);
            taskRepository.save(existingTaskEntity);
            return TaskConverter.convertToDTO(existingTaskEntity);
        }
        return null;
    }

    @Override
    public String getTaskTitle(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null) ? taskEntity.getTitle() : null;
    }

    @Override
    public String getTaskDescription(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null) ? taskEntity.getDescription() : null;
    }

    @Override
    public String getTaskStatus(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null) ? taskEntity.getStatus().toString() : null;
    }

    @Override
    public String getTaskPriority(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null) ? taskEntity.getPriority().toString() : null;
    }

    @Override
    public String getTaskDueDate(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null && taskEntity.getDueDate() != null) ? taskEntity.getDueDate().toString() : null;
    }

    @Override
    public List<String> getTaskAssignedUsers(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        return (taskEntity != null && taskEntity.getAssignedUsers() != null)
                ? taskEntity.getAssignedUsers().stream().map(UserEntity::getUsername).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        List<TaskEntity> tasks = taskRepository.findByAssignedUsersUserId(userId);
        return tasks.stream()
                .map(TaskConverter::convertToDTO)
                .collect(Collectors.toList());
    }
 
}
