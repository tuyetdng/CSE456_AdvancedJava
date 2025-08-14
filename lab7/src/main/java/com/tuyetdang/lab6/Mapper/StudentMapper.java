package com.tuyetdang.lab6.Mapper;

import com.tuyetdang.lab6.Dto.StudentRequestDto;
import com.tuyetdang.lab6.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "major", ignore = true)
    Student toEntity(StudentRequestDto dto);

    @Mapping(target = "major", ignore = true)
    void updateStudent(@MappingTarget Student student, StudentRequestDto dto);
}
