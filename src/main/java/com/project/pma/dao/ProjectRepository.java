package com.project.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
}
