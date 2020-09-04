package com.shubham.cache.repository;

import com.shubham.cache.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student,Long> {

    Student getById(Long id);

}
