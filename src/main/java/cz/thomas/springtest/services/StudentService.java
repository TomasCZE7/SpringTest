package cz.thomas.springtest.services;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.StudentDTO;
import cz.thomas.springtest.dto.mapping.MarkMapper;
import cz.thomas.springtest.dto.mapping.StudentMapper;
import cz.thomas.springtest.repository.MarkRepository;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.repository.entity.Mark;
import cz.thomas.springtest.repository.entity.Student;
import org.hibernate.annotations.SQLDelete;
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

    @Autowired
    MarkService markService;


    public void createStudent(StudentDTO studentDTO, Long classId) {
        Student entity = studentMapper.fromDTO(studentDTO, classId);
        studentRepository.save(entity);
        for(MarkDTO markDTO : studentDTO.getGraduationMarks()){
            markService.addMark(markDTO, entity.getId());
        }
    }
    public void createStudent(Student student) {
        studentRepository.save(student);
        for(Mark mark : student.getGraduationMarks()){
            markService.addMark(mark);
        }
    }

    public StudentDTO getStudent(Long studentId) {
        Optional<Student> returnValue = studentRepository.findById(studentId);
        return returnValue.map(student -> studentMapper.toDTO(student)).orElse(null);
    }

    public void deleteStudent(Student student) {
        for(Mark mark : student.getGraduationMarks()){
            markRepository.delete(mark);
        }
        studentRepository.delete(student);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> result = studentRepository.findById(studentId);
        if(!result.isPresent()){
            return;
        }
        deleteStudent(result.get());
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
