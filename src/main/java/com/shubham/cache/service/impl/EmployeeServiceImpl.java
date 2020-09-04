package com.shubham.cache.service.impl;

import com.shubham.cache.config.cache.EmployeeCacheIdentifier;
import com.shubham.cache.entity.Employee;
import com.shubham.cache.repository.EmployeeDao;
import com.shubham.cache.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    @Cacheable(value = "employee", keyGenerator = "employeeKeyGenerator-1")
    public Employee getById(Long id) {
        return employeeDao.getById(id);
    }

    @Override
    @CachePut(value = "employee", keyGenerator = "employeeKeyGenerator-2")
    public Employee updateEmployee(Employee employee) {
        if(employeeDao.existsById(employee.getId())) {
            return employeeDao.save(employee);
        }else {
            throw new IllegalArgumentException("An employee must have an id");
        }
    }

    @Override
    @CacheEvict(value = "employee",keyGenerator = "employeeKeyGenerator-1")
    public void deleteEmployee(Long id) {
        if(employeeDao.existsById(id)){
         employeeDao.existsById(id);
        }
    }


}
