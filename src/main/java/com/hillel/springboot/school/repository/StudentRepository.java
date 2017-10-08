package com.hillel.springboot.school.repository;

import com.hillel.springboot.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
