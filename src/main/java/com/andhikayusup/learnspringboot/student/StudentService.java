package com.andhikayusup.learnspringboot.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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

	@Transactional
	public Student updateStudent(Long studentId, String name, String email){
		Student student = studentRepository.findById(studentId).orElseThrow(
			() -> new IllegalStateException("Student not found")
		);

		if (name != null &&
				name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}

		return student;
	}
}
