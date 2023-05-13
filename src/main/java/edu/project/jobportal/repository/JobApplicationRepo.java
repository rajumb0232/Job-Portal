package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

}
