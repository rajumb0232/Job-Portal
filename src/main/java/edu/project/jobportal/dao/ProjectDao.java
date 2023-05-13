package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import edu.project.jobportal.repository.ProjectRepo;

public class ProjectDao {
	
	@Autowired
	private ProjectRepo projectRepo;
}
