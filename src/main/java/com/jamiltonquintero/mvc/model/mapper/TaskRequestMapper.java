package com.jamiltonquintero.mvc.model.mapper;

import com.jamiltonquintero.mvc.model.dto.request.TaskRequest;
import com.jamiltonquintero.mvc.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") 
public interface TaskRequestMapper {


    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Task toDomain(TaskRequest request);
}
