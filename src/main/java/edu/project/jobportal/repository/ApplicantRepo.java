package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Long>{

}
