package com.student.springboot.web.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.springboot.web.boot.Students;

public interface StudentRepository extends JpaRepository<Students,Long > {



}
