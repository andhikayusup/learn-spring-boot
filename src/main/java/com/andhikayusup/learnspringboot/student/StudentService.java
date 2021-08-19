package com.andhikayusup.learnspringboot.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}
	
    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student){
		Optional<Student> studentOptional =	studentRepository.findStudentByEmail(student.getEmail());

		if(studentOptional.isPresent()){
			throw new IllegalAccessError("Email Taken");
		}

		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if (!exists){
			throw new IllegalStateException("Student not found");
		}

		studentRepository.deleteById(studentId);
	}
}
