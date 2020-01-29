package cz.thomas.springtest.services;

import cz.thomas.springtest.dto.SchoolClassDTO;
import cz.thomas.springtest.dto.StudentDTO;
import cz.thomas.springtest.dto.mapping.ClassMapper;
import cz.thomas.springtest.dto.mapping.StudentMapper;
import cz.thomas.springtest.repository.ClassRepository;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.repository.entity.SchoolClass;
import cz.thomas.springtest.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentService studentService;

    public void createClass(SchoolClassDTO classDTO) {
        createClass(classMapper.fromDTO(classDTO));
    }

    public void createClass(SchoolClass schoolClass) {
        classRepository.save(schoolClass);
        for(Student student : schoolClass.getStudents()){
            addStudent(student, schoolClass.getId());
        }
    }

    public void addStudent(Student student, Long classId) {
        studentService.createStudent(student, classId);
    }

    public void removeClass(Long classId){
        Optional<SchoolClass> result = classRepository.findById(classId);
        if(!result.isPresent()){
            return;
        }
        SchoolClass schoolClass = result.get();
        for (Student student :
                schoolClass.getStudents()) {
            studentService.deleteStudent(student);
        }
    }

    public SchoolClassDTO getSpecificClass(Long classId) {
        Optional<SchoolClass> result = classRepository.findById(classId);
        if(!result.isPresent()){
            return null;
        }
        return classMapper.toDTO(result.get());
    }

    public List<SchoolClassDTO> getAllClasses() {
        List<SchoolClassDTO> result = new ArrayList<>();
        for (SchoolClass schoolClass : classRepository.findAll()){
            result.add(classMapper.toDTO(schoolClass));
        }
        return result;
    }
}
