package com.shubham.cache.service;


import com.shubham.cache.entity.Employee;

public interface EmployeeService {

    Employee getById(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

}
