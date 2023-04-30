package com.project.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	@Override
	public List<Project> findAll();
}
