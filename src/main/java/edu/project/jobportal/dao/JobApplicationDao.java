package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.repository.JobApplicationRepo;

@Repository
public class JobApplicationDao {
	
	@Autowired
	private JobApplicationRepo jobApplicationRepo;
	
	public JobApplication createJobApplication(JobApplication jobApplication) {
		return jobApplicationRepo.save(jobApplication);
	}
}
