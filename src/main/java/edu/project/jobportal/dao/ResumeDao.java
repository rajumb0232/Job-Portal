package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import edu.project.jobportal.repository.ResumeRepo;

public class ResumeDao {
	
	@Autowired
	private ResumeRepo resumeRepo;
}
