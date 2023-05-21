package com.project.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.project.pma.dto.EmployeeProject;
import com.project.pma.entities.Employee;

import jakarta.validation.Valid;

@RepositoryRestResource(collectionResourceRel="apiemployees",path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	@Query(nativeQuery=true, value="select e.first_name as FIRSTNAME,e.last_name as LASTNAME,COUNT(pe.employee_id) as PROJECTCOUNT"
			+ " FROM employee e left join project_employee pe on pe.employee_id = e.employee_id GROUP BY e.first_name"
			+ ",e.last_name order by 3 DESC")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	public Iterable<Employee> findAll();

	public Employee findById(Long id);

	public Employee save(@Valid Employee employee);

	public void deleteById(Long id);
	
//	public Employee findByEmployeeId(long id);
}
