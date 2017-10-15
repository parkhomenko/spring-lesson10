package com.hillel.springboot.school.controller;

import com.hillel.springboot.school.model.Student;
import com.hillel.springboot.school.repository.StudentRepository;
import com.hillel.springboot.school.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getAll() {
        throw new RuntimeException("Just an exception");
        //return studentService.getAll2();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student get(@PathVariable Long id) {
        return studentRepository.findOne(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public void create(@RequestBody Student student) {
        studentRepository.saveAndFlush(student);
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public void update(@RequestBody Student student) {
        Student studentFromDb = studentRepository.findOne(student.getId());
        BeanUtils.copyProperties(student, studentFromDb);
        studentRepository.saveAndFlush(studentFromDb);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        studentRepository.delete(id);
    }
}
