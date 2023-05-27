package edu.project.jobportal.dao;

import java.util.Optional;

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

	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}

	public Optional<Project> getProjectById(long projectId) {
		return projectRepo.findById(projectId);
	}
}
