package com.jamiltonquintero.mvc.model.mapper;

import com.jamiltonquintero.mvc.model.dto.TaskDto;
import com.jamiltonquintero.mvc.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") 
public interface TaskDtoMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public TaskDto toDto(Task domain);

}
