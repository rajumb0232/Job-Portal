package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Project;
import edu.project.jobportal.repository.ProjectRepo;

@Repository
public class ProjectDao {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	public Project saveProject(Project project) {
		return projectRepo.save(project);
	}
}
