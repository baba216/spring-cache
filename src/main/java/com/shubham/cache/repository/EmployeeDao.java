package com.shubham.cache.repository;

import com.shubham.cache.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee,Long> {

    Employee getById(Long id);
}
