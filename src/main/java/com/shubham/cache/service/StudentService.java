package com.shubham.cache.service;

import com.shubham.cache.entity.Student;

public interface StudentService {

    Student getById(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);
}
