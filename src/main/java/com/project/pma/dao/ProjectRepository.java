package com.project.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.pma.dto.ChartData;
import com.project.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	public List<Project> findAll();
	
	
	@Query(nativeQuery=true, value="select stage as Label,COUNT(*) as count from project group by stage")
	public List<ChartData> getProjectStatus();


	public Project findById(Long id);


	public Project save(Project project);


	public void deleteById(Long id);
	
}
