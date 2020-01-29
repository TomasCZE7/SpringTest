package cz.thomas.springtest.contollers;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.StudentDTO;

import cz.thomas.springtest.repository.MarkRepository;
import cz.thomas.springtest.repository.StudentRepository;
import cz.thomas.springtest.services.MarkService;
import cz.thomas.springtest.services.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    MarkService markService;

    @Autowired
    StudentService studentService;

    @ApiOperation(	value = "Prints 'Hello World'"  )
    @GetMapping("/helloworld")
    public String HelloWorld(){
        return "Hello World!";
    }

    @ApiOperation(	value = "Creates a student",
            notes = "Creates a student with specific student ID.", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/student/create", consumes = "application/json")
    @Transactional
    public void createStudent(@NotNull  @Valid @RequestBody StudentDTO studentDTO){
        studentService.createStudent(studentDTO);
    }

    @ApiOperation(	value = "Adds a mark",
            notes = ".", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/mark/create", consumes = "application/json")
    @Transactional
    public void addMark(@RequestBody @Valid @NotNull MarkDTO markDTO, Long studentId){
        markService.addMark(markDTO, studentId);
    }

    @ApiOperation(	value = "Deletes a mark",
            notes = "Deletes a mark based on a markID", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/mark/{markId}/delete")
    @Transactional
    public void deleteMark(@NotNull @PathVariable Long markId) {
        markService.deleteMark(markId);
    }

    @ApiOperation(	value = "Show data of specific student",
            notes = "Returns a json containing data of specific student.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/student/{studentId}")
    @Transactional
    public StudentDTO getStudent(@NotNull @PathVariable Long studentId) {
        return studentService.getStudent(studentId);

    }

    @ApiOperation(	value = "Show all students",
            notes = "Returns a json of all students and their data.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/student/")
    public List<StudentDTO> showAllStudents(){
        return studentService.getAllStudents();
    }

    @ApiOperation(	value = "Deletes a student",
            notes = "Deletes a student based on a studentID", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/student/{studentId}/delete")
    @Transactional
    public void deleteStudent(@NotNull @PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }


}
