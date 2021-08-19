package com.andhikayusup.learnspringboot.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

    @PostMapping
    public Student registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        return student;
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent(
        @PathVariable("studentId") Long studentId, 
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String email) {
        return studentService.updateStudent(studentId, name, email);
    }
}
