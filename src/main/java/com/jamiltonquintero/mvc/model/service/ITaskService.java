package com.jamiltonquintero.mvc.model.service;

import com.jamiltonquintero.mvc.model.dto.TaskDto;
import com.jamiltonquintero.mvc.model.dto.request.TaskRequest;

import java.util.List;

public interface ITaskService {

    TaskDto create(TaskRequest request);
    TaskDto getById(Long id);
    List<TaskDto> getAll();
    void deleteById(Long id);

    TaskDto update(TaskRequest request, Long id);
}
