package com.project.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
