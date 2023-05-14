package com.project.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.pma.entities.Employee;
import com.project.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee emp = new Employee();
		model.addAttribute("emp",emp);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee emp, Model model) {
		//Handle Saving Employees
		empService.save(emp);
		
		//redirecting to prevent duplicate submission
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String displayHome(Model model) {
		
		List<Employee> employees = empService.getAll();
		model.addAttribute("employees",employees);
		
		return "employees/list-employees";
	}
}
