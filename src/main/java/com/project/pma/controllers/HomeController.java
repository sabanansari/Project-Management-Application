package com.project.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.pma.dao.EmployeeRepository;
import com.project.pma.dao.ProjectRepository;
import com.project.pma.entities.Employee;
import com.project.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects",projects);
		
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees",employees);
		
		return "main/home";
	}
}
