package com.project.pma.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long projectId;
	private String name;
	
	private String stage;     //NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	public Project(){
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
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

	
	
	
	
}
