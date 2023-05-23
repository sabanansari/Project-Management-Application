package com.project.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pma.dao.ProjectRepository;
import com.project.pma.dto.ChartData;
import com.project.pma.dto.TimeChartData;
import com.project.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}

	public Project findByProjectId(long id) {
		return proRepo.findByProjectId(id);
	}
	
	public void delete(Project proj) {
		proRepo.delete(proj);
	}
	
	public List<TimeChartData> getTimeData(){
		return proRepo.getTimeData();
	}
}
