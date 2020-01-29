package cz.thomas.springtest.dto.mapping;

import cz.thomas.springtest.dto.StudentDTO;
import cz.thomas.springtest.repository.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

     public abstract StudentDTO toDTO(Student source);

     public abstract Student fromDTO(StudentDTO source);

}
