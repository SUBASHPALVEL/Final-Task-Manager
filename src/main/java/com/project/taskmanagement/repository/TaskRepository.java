package com.project.taskmanagement.repository;

import com.project.taskmanagement.entity.TaskEntity;
import com.project.taskmanagement.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByAssignedUsersUserId(Long userId);
}
