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

import com.example.error.CustomErrorType;
import com.example.model.Student;
import com.example.util.DateUtil;


@RestController
@RequestMapping("students")
public class StudentEndpoint {
	private DateUtil dateUtil;
	@Autowired
	public StudentEndpoint(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	@GetMapping
	public ResponseEntity<?> listAll(){
//		System.out.println("+++++++"+dateUtil.formatLocalDateTimeDatabaseStyle(LocalDateTime.now()));
		return new ResponseEntity<>(Student.studentList,HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id){
		Student student = new Student();
		student.setId(id);		
		long index = Student.studentList.indexOf(student);
		if(index == -1) {
			return new ResponseEntity<>(new CustomErrorType("Student not Found@@@"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(Student.studentList.get((int)index), HttpStatus.OK);
	}
	@PostMapping	
	public ResponseEntity<?> save(@RequestBody Student student){
		Student.studentList.add(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Student student){
		Student.studentList.remove(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student){
		Student.studentList.remove(student);
		Student.studentList.add(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	
}
