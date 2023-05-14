package com.project.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.pma.entities.Employee;
import com.project.pma.entities.Project;
import com.project.pma.services.EmployeeService;
import com.project.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = empService.getAll();
		model.addAttribute("project",aProject);
		model.addAttribute("allEmployees",employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		//Handle Saving to Database
		proService.save(project);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping
	public String displayHome(Model model) {
		
		List<Project> projects = proService.getAll();
		model.addAttribute("projects",projects);
		
		return "projects/list-projects";
	}
	
}
