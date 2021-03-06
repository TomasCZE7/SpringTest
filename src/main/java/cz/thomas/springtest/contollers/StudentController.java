package cz.thomas.springtest.contollers;

import cz.thomas.springtest.dto.MarkDTO;
import cz.thomas.springtest.dto.SchoolClassDTO;
import cz.thomas.springtest.dto.StudentDTO;

import cz.thomas.springtest.services.MarkService;
import cz.thomas.springtest.services.SchoolClassService;
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
    MarkService markService;

    @Autowired
    StudentService studentService;

    @Autowired
    SchoolClassService schoolClassService;

    @ApiOperation(	value = "Prints 'Hello World'"  )
    @GetMapping("/helloworld")
    public String HelloWorld(){
        return "Hello World!";
    }

    @ApiOperation(	value = "Creates a student",
            notes = "Creates a student based on json file.", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/student/create", consumes = "application/json")
    @Transactional
    public void addStudent(@NotNull @Valid @RequestBody StudentDTO studentDTO, Long classId){
        studentService.createStudent(studentDTO, classId);
    }

    @ApiOperation(	value = "Creates a class of students",
            notes = "Creates a class based on json file.", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/class/create", consumes = "application/json")
    @Transactional
    public void createClass(@NotNull @Valid @RequestBody SchoolClassDTO classDTO){
        schoolClassService.createClass(classDTO);
    }

    @ApiOperation(	value = "Adds a mark",
            notes = "Creates a mark and assigns it to the specified student by his id.", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/mark/create", consumes = "application/json")
    @Transactional
    public void addMark(@RequestBody @Valid @NotNull MarkDTO markDTO, @NotNull @PathVariable Long studentId){
        markService.addMark(markDTO, studentId);
    }

    @ApiOperation(	value = "Deletes a mark",
            notes = "Deletes a mark based on a markID"  )
    @DeleteMapping("/mark/{markId}/delete")
    @Transactional
    public void deleteMark(@NotNull @PathVariable Long markId) {
        markService.deleteMark(markId);
    }

    @ApiOperation(	value = "Show data of specific student",
            notes = "Returns a json containing data of specific student.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/student/{studentId}")
    public StudentDTO getStudent(@NotNull @PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @ApiOperation(	value = "Show all classes",
            notes = "Returns a json of all classes and their data.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/class/")
    public List<SchoolClassDTO> showAllClasses(){
        return schoolClassService.getAllClasses();
    }

    @ApiOperation(	value = "Show data of specific class",
            notes = "Returns a json containing data of specific class.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/class/{classId}")
    public SchoolClassDTO getClass(@NotNull @PathVariable Long classId) {
        return schoolClassService.getSpecificClass(classId);
    }

    @ApiOperation(	value = "Remove specific class",
            notes = "Removes class specified by classId." )
    @GetMapping(path = "/class/{classId}/remove")
    @Transactional
    public void removeClass(@NotNull @PathVariable Long classId) {
        schoolClassService.removeClass(classId);
    }




    @ApiOperation(	value = "Show all students",
            notes = "Returns a json of all students and their data.", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/student/")
    public List<StudentDTO> showAllStudents(){
        return studentService.getAllStudents();
    }

    @ApiOperation(	value = "Deletes a student",
            notes = "Deletes a student based on a studentID." )
    @DeleteMapping("/student/{studentId}/delete")
    @Transactional
    public void deleteStudent(@NotNull @PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }


}
