package com.project.pma.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Project {
	
	@Id
	@SequenceGenerator(name = "project_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_seq")
	private long projectId;
	private String name;
	
	private String stage;     //NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	
	@NotNull(message = "date cannot be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message = "date cannot be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", joinColumns=@JoinColumn(name="project_id"),inverseJoinColumns=@JoinColumn(name="employee_id"))
	@JsonIgnore
	private List<Employee> employees;
	
	public Project(){
		    
	} 
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		if(employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(employee);
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

//	public Project get() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
	
}
