package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Job;

public interface JobRepo extends JpaRepository<Job, Long>{
	
	// should create method to find jobs by Skill
}
