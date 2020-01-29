package cz.thomas.springtest.services;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.StudentDTO;
import cz.thomas.springtest.dto.mapping.MarkMapper;
import cz.thomas.springtest.dto.mapping.StudentMapper;
import cz.thomas.springtest.repository.MarkRepository;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.repository.entity.Mark;
import cz.thomas.springtest.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    MarkMapper markMapper;


    public void createStudent(StudentDTO studentDTO) {
        Student entity = studentMapper.fromDTO(studentDTO);
        studentRepository.save(entity);
        for(MarkDTO markDTO : studentDTO.getGraduationMarks()){
            Mark mark = markMapper.fromDTO(markDTO, entity.getId());
            markRepository.save(mark);
        }
    }

    public StudentDTO getStudent(Long studentId) {
        Optional<Student> returnValue = studentRepository.findById(studentId);
        return returnValue.map(student -> studentMapper.toDTO(student)).orElse(null);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> result = new ArrayList<>();
        for (Student student:
                studentRepository.findAll()) {

            result.add(studentMapper.toDTO(student));
        }
        return result;
    }
}
