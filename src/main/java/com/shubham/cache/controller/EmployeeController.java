package com.shubham.cache.controller;

import com.shubham.cache.entity.Employee;
import com.shubham.cache.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Employee getEmployeeById(@RequestParam("id") Long id){
        LOGGER.info("Get employee by id:{}",id);
        return employeeService.getById(id);
    }

    @PutMapping("/employee")
    public Employee updateEmployeeById(@RequestBody Employee employee){
        LOGGER.info("Get employee by id:{}",employee.getId());
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employee")
    public void updateEmployeeById(@RequestParam Long id){
        LOGGER.info("Get employee by id:{}",id);
        employeeService.deleteEmployee(id);
    }
}
