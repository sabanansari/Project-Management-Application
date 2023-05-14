package com.project.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pma.dao.EmployeeRepository;
import com.project.pma.dto.EmployeeProject;
import com.project.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public List<Employee> getAll(){
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return empRepo.employeeProjects();
	}
}
