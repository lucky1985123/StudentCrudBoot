package com.student.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StudentOnSpringBootApplication {

	public static void main(String[] args) {
		/* AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(StudentOnSpringBootApplication.class);
	        ctx.getBean(StudentController.class);*/
		SpringApplication.run(StudentOnSpringBootApplication.class, args);
		System.out.println("StudentApplication");
	}
	

}

