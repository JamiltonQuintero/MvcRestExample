package com.jamiltonquintero.mvc.model.service.impl;

import com.jamiltonquintero.mvc.common.util.constant.TaskConstant;
import com.jamiltonquintero.mvc.model.dto.TaskDto;
import com.jamiltonquintero.mvc.model.dto.request.TaskRequest;
import com.jamiltonquintero.mvc.model.entity.Task;
import com.jamiltonquintero.mvc.model.mapper.TaskDtoMapper;
import com.jamiltonquintero.mvc.model.mapper.TaskRequestMapper;
import com.jamiltonquintero.mvc.model.repository.TaskRepository;
import com.jamiltonquintero.mvc.model.service.ITaskService;
import com.jamiltonquintero.mvc.common.exception.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final TaskRequestMapper taskRequestMapper;
    private final TaskDtoMapper taskDtoMapper;
    public TaskServiceImpl(TaskRepository taskRepository,TaskRequestMapper taskRequestMapper, TaskDtoMapper taskDtoMapper) {
        this.taskRepository = taskRepository;
        this.taskRequestMapper = taskRequestMapper;
        this.taskDtoMapper = taskDtoMapper;
    }

    @Override
    public TaskDto create(TaskRequest request) {

        var newTaskRequest = taskRequestMapper.toDomain(request);
        newTaskRequest.setInitialValues();

        var createdTask = taskRepository.save(newTaskRequest);
        return taskDtoMapper.toDto(createdTask);

    }

    @Override
    public TaskDto getById(Long id) {

        var optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()){
            throw new TaskException(HttpStatus.NOT_FOUND, String.format(TaskConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }

       return taskDtoMapper.toDto(optionalTask.get());

    }

    @Override
    public List<TaskDto> getAll() {

        var optionalTasks = taskRepository.findAll();

        return optionalTasks.stream()
                .map(taskDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto update(TaskRequest request, Long id) {

        var optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()){
            throw new TaskException(HttpStatus.NOT_FOUND, String.format(TaskConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }

        var taskToUpdate = optionalTask.get();
        setValuesToUpdate(request,taskToUpdate);

        var updatedTask = taskRepository.save(taskToUpdate);

        return taskDtoMapper.toDto(updatedTask);
    }

    private void setValuesToUpdate(TaskRequest request, Task currentTask){
        currentTask.setName(request.getName());
        currentTask.setDescription(request.getDescription());
    }

}
