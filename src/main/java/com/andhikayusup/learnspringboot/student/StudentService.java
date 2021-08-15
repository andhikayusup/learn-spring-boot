package com.andhikayusup.learnspringboot.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StudentService {
    public List<Student> getStudents() {
		return List.of(
			new Student(1L, "Andhika", "andhika@gmail.com", LocalDate.of(2000, 01, 01), 21),
			new Student(1L, "Andhika", "andhika@gmail.com", LocalDate.of(2000, 01, 01), 21)
		);
	}
}
