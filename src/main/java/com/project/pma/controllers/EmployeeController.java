package com.project.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.pma.entities.Employee;
import com.project.pma.services.EmployeeService;

import jakarta.validation.Valid;

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
	public String createEmployee(@Valid Employee emp, Model model,Errors errors) {
		
		if(errors.hasErrors()) {
			return "redirect:/employees/new";
		}
		//Handle Saving Employees
		empService.save(emp);
		
		//redirecting to prevent duplicate submission
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String displayHome(Model model) {
		
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employees",employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long id,Model model) {
		
		Employee theEmp = empService.findByEmployeeId(id);
		
		model.addAttribute("emp",theEmp);
		
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id, Model model) {
		Employee emp = empService.findByEmployeeId(id);
		empService.delete(emp);
		return "redirect:/employees";
	}
}
