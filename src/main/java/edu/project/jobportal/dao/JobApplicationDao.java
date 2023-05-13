package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import edu.project.jobportal.repository.JobApplicationRepo;

public class JobApplicationDao {
	
	@Autowired
	private JobApplicationRepo jobApplicationRepo;
}
