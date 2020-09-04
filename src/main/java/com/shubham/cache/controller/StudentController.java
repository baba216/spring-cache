package com.shubham.cache.controller;

import com.shubham.cache.entity.Student;
import com.shubham.cache.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public Student getStudentById(@RequestParam("id") Long id){
        LOGGER.info("Get student by id:{}",id);
        return studentService.getById(id);
    }

    @PutMapping("/student")
    public Student updateStudentById(@RequestBody Student student){
        LOGGER.info("Get student by id:{}",student.getId());
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/student")
    public void updateStudentById(@RequestParam Long id){
        LOGGER.info("Get student by id:{}",id);
        studentService.deleteStudent(id);
    }
}
