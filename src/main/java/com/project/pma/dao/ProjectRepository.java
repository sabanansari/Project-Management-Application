package com.project.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.pma.dto.ChartData;
import com.project.pma.dto.TimeChartData;
import com.project.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	public List<Project> findAll();
	
	
	@Query(nativeQuery=true, value="select stage as Label,COUNT(*) as count from project group by stage")
	public List<ChartData> getProjectStatus();


	public Project findById(Long id);


	public Project save(Project project);


	public void deleteById(Long id);


	public Project findByProjectId(long id);


	public void delete(Project proj);
	
	
	@Query(nativeQuery = true, value="SELECT name as name, start_date as startDate, end_date as endDate from project")
	public List<TimeChartData> getTimeData();
	
}
