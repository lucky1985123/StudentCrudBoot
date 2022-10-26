package com.student.springboot.web.spring;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.student.springboot.web.boot.Students;
import com.student.springboot.web.spring.repository.StudentRepository;

import ch.qos.logback.core.util.TimeUtil;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Students> fetchStudentsGtThanAge(Integer age) {
		List<Students> allStundents = studentRepository.findAll();
		List<Students> filteredStudents = null;
		if (!CollectionUtils.isEmpty(allStundents)) {
			filteredStudents = filterStudentsByAge(allStundents, age);
		}
		return filteredStudents;

	}

	public List<Students> fetchStudentsByFirstName(String name) {
		List<Students> allStundents = studentRepository.findAll();
		List<Students> filteredStudents = null;
		if (!CollectionUtils.isEmpty(allStundents)) {
			filteredStudents = filterStudentsByFirstName(allStundents, name);
		}
		return filteredStudents;
	}
	public List<Students> fetchStudentsByLastName(String name) {
		List<Students> allStundents = studentRepository.findAll();
		List<Students> filteredStudents1 = null;
		if (!CollectionUtils.isEmpty(allStundents)) {
			filteredStudents1 = filterStudentsByLastName(allStundents, name);
		}
		return filteredStudents1;
	}


	private List<Students> filterStudentsByFirstName(List<Students> allStundents, String name) {
		List<Students> nameFilteredList = new ArrayList<Students>();
		for(Students  tmp : allStundents) {
			String studentName = tmp.getFirstName();
			if(studentName.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
				nameFilteredList.add(tmp);
			}
		}
		return nameFilteredList;
	}
	
	private List<Students> filterStudentsByLastName(List<Students> allStundents, String name) {
		List<Students> nameFilteredList = new ArrayList<Students>();
		for(Students  tmp : allStundents) {
			String studentName = tmp.getLastName();
			if(studentName.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
				nameFilteredList.add(tmp);
			}
		}
		return nameFilteredList;
	}
	
	
	
	
	
	


	private List<Students> filterStudentsByAge(List<Students> allStundents, Integer age) {
		List<Students> ageFilteredList = new ArrayList<Students>();
		for(Students  tmp : allStundents) {
	        Integer studentAge = calcStudentAge(tmp.getDob());
	        if(studentAge > age) {
	        	ageFilteredList.add(tmp);
	        }
		}
		return ageFilteredList;
	}

	private Integer calcStudentAge(Date dob) {
		long timeDiffInMillis = Calendar.getInstance().getTimeInMillis() - dob.getTime();
		Long daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiffInMillis);
		Long years = daysDiff/365;
		return Integer.valueOf(years.toString());
	}

}
