package com.student.springboot.web.spring;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.springboot.web.boot.Students;
import com.student.springboot.web.spring.repository.StudentRepository;
import com.student.springboot.web.spring.resource.ResourceNotFoundException;

 
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;
	
	// get all employees
	@GetMapping("/student")
	public List<Students> getAllStudents(){
		return studentRepository.findAll();
	}

	@GetMapping ("/student/searchf/{firstname}")
	public List<Students> studentByFirstName(@PathVariable String firstname){
		 return studentService.fetchStudentsByFirstName(firstname);
	}
	@GetMapping ("/student/searchl/{lastname}")
	public List<Students> studentByLastName(@PathVariable String lastname){
		return studentService.fetchStudentsByLastName(lastname);
	}

	
	@GetMapping("/student/searchByAge")
	public List<Students> getStudents( @RequestParam Integer age) {
		List<Students> students = studentService.fetchStudentsGtThanAge(age);
		return students;
	}
	
	
	
	
	// create employee rest api
	@PostMapping("/student")
	public Students createSudents(@RequestBody Students students) {
		System.out.println("Request came to  createSudents method");
		return studentRepository.save(students);
	}
	
	
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Students> updateStudents(@PathVariable Long id, @RequestBody Students studentsDetails){
		Students students = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Students not exist with id :" + id));
		
		students.setFirstName(studentsDetails.getFirstName());
		students.setLastName(studentsDetails.getLastName());
		
		if(studentsDetails.getDob() != null) {
			students.setDob(studentsDetails.getDob());
					
		}
		
		Students updatedStudents = studentRepository.save(students);
		return ResponseEntity.ok(updatedStudents);
	}
	
	// delete employee rest api
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudents(@PathVariable Long id){
		Students students  = studentRepository.findById(id)
				.orElseThrow(() -> new com.student.springboot.web.spring.resource.ResourceNotFoundException("Students not exist with id :" + id));
		
		studentRepository.delete(students);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}