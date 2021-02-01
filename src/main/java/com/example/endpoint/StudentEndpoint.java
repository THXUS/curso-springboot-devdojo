package com.example.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.repository.StudentRepository;


@RestController
@RequestMapping("students")
public class StudentEndpoint {
	private final StudentRepository studentDAO;
	@Autowired
	public StudentEndpoint(StudentRepository studentDAO) {
		super();
		this.studentDAO = studentDAO;
	}

	@GetMapping
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(studentDAO.findAll(),HttpStatus.OK); 
	}
	

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
		Student student = studentDAO.findById(id).orElseThrow(()-> new IllegalStateException("Esse parça não consta"));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	@PostMapping	
	public ResponseEntity<?> save(@RequestBody Student student){
		return new ResponseEntity<>(studentDAO.save(student),HttpStatus.OK);
	}
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		studentDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student){
		studentDAO.save(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	
}
