package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	
	private final StudentRepository studentRepository;
	
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@GetMapping(value="/home",produces=MediaType.TEXT_PLAIN_VALUE)
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/creation")
	public String create() {
		Arrays.asList(new Student("AA",11),
					  new Student("BB",22),
					  new Student("CC",33))
					.forEach(studentRepository::save);
		
		return "successfully created.";	
	}
	
	@GetMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> showAllStudents(){
		return this.studentRepository.findAll();
	}
	
	@GetMapping(value="/all/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Student findStudentById(@PathVariable Long id) {
		return this.studentRepository.getOne(id);
	}

}
