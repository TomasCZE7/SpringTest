package cz.thomas.springtest.dto.mapping;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.repository.entity.Mark;
import cz.thomas.springtest.repository.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class MarkMapper {

    @Autowired
    StudentRepository studentRepository;

    public abstract MarkDTO toDTO(Mark source);

    @Mapping(target = "student", expression = "java(getStudent(studentId))")
    public abstract Mark fromDTO(MarkDTO source, Long studentId);

    Student getStudent(Long studentId){
        Optional<Student> output = studentRepository.findById(studentId);
        if(!output.isPresent()){
            return null;
        }
        return output.get();
    }

}
