package com.jamiltonquintero.mvc.model.repository;

import com.jamiltonquintero.mvc.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}