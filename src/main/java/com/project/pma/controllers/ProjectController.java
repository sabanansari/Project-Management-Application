package com.project.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pma.dto.TimeChartData;
import com.project.pma.entities.Employee;
import com.project.pma.entities.Project;
import com.project.pma.services.EmployeeService;
import com.project.pma.services.ProjectService;

import jakarta.validation.Valid;

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
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("project",aProject);
		model.addAttribute("allEmployees",employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(@Valid Project project, Model model,Errors errors) {
		
		if(errors.hasErrors()) {
			return "redirect:/projects/new";
		}
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
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id,Model model) {
		
		Project theProj = proService.findByProjectId(id);
		
		model.addAttribute("project",theProj);
		
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id, Model model) {
		Project theProj = proService.findByProjectId(id);
		proService.delete(theProj);
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData = proService.getTimeData();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		
		model.addAttribute("projectTimeList",jsonTimelineString);
		
		return "projects/project-timelines";
	}
	
}
