package com.example.model;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;


public class Student {
	
	private long id;
	private String name;
	public static List<Student> studentList;
	
	static {
		studentRepository();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Student(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Student(String name) {
		this.name = name;
	}

	public Student() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static void studentRepository() {
		studentList  = new ArrayList<Student>(asList(new Student(1,"Joazinho"), new Student(2,"Rodolfo")));
	}
	
}
