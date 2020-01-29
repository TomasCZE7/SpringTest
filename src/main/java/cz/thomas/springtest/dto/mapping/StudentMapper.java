package cz.thomas.springtest.dto.mapping;

import cz.thomas.springtest.dto.StudentDTO;
import cz.thomas.springtest.repository.ClassRepository;
import cz.thomas.springtest.repository.entity.SchoolClass;
import cz.thomas.springtest.repository.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

     @Autowired
     ClassRepository classRepository;

     public abstract StudentDTO toDTO(Student source);

     @Mapping(target = "partOfClass", expression = "java(findClassById(classId))")
     public abstract Student fromDTO(StudentDTO source, Long classId);

     SchoolClass findClassById(Long classId){
          Optional<SchoolClass> result = classRepository.findById(classId);
          if(!result.isPresent()){
               return null;
          }
          return result.get();
     }

}
