package com.shubham.cache.service.impl;

import com.shubham.cache.entity.Student;
import com.shubham.cache.repository.StudentDao;
import com.shubham.cache.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Override
    @Cacheable(value = "student", cacheManager = "studentCacheManager", keyGenerator = "studentKeyGenerator")
    public Student getById(Long id) {
        return studentDao.getById(id);
    }

    @Override
    @CachePut(value = "student", cacheManager = "studentCacheManager", keyGenerator = "studentKeyGenerator")
    public Student updateStudent(Student student) {
        if (studentDao.existsById(student.getId())) {
            return studentDao.save(student);
        } else {
            throw new IllegalArgumentException("An employee must have an id");
        }
    }

    @Override
    @CacheEvict(value = "student", cacheManager = "studentCacheManager", keyGenerator = "studentKeyGenerator")
    public void deleteStudent(Long id) {
        if (studentDao.existsById(id)) {
            studentDao.existsById(id);
        }
    }
}
