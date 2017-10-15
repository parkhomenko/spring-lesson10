package com.hillel.springboot.school.service;

import com.hillel.springboot.school.model.Student;
import com.hillel.springboot.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> getAll2() {
        return getAll();
    }
}
