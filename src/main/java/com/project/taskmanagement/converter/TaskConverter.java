package com.project.taskmanagement.converter;

import com.project.taskmanagement.dto.TaskDTO;
import com.project.taskmanagement.entity.TaskEntity;

public class TaskConverter {

    public static TaskEntity convertToEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskId(taskDTO.getTaskId());
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setDueDate(taskDTO.getDueDate());
        taskEntity.setCreatedDate(taskDTO.getCreatedDate());
        taskEntity.setCompletedDate(taskDTO.getCompletedDate());
        taskEntity.setAssignedUsers(taskDTO.getAssignedUsers());
        return taskEntity;
    }

    public static TaskDTO convertToDTO(TaskEntity taskEntity) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(taskEntity.getTaskId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setStatus(taskEntity.getStatus());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setDueDate(taskEntity.getDueDate());
        taskDTO.setCreatedDate(taskEntity.getCreatedDate());
        taskDTO.setCompletedDate(taskEntity.getCompletedDate());
        taskDTO.setAssignedUsers(taskEntity.getAssignedUsers());
        return taskDTO;
    }
}

