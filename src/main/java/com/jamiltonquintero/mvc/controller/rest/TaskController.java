package com.jamiltonquintero.mvc.controller.rest;

import com.jamiltonquintero.mvc.model.dto.TaskDto;
import com.jamiltonquintero.mvc.model.dto.request.TaskRequest;
import com.jamiltonquintero.mvc.model.service.ITaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService iTaskService;

    public TaskController(ITaskService iTaskService) {
        this.iTaskService = iTaskService;
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable long id){
        return iTaskService.getById(id);
    }

    @GetMapping
    public List<TaskDto> getAll() {
        return iTaskService.getAll();
    }

    @PostMapping()
    public TaskDto create(@RequestBody TaskRequest taskRequest){
        return iTaskService.create(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskDto userEdit(@RequestBody TaskRequest taskRequest,
                               @PathVariable Long id){
        return iTaskService.update(taskRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        iTaskService.deleteById(id);
    }


}
