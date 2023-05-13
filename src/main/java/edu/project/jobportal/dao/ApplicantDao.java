package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.repository.ApplicantRepo;

public class ApplicantDao {
	
	@Autowired
	private ApplicantRepo applicantRepo;
	
	public Applicant addApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}
}
