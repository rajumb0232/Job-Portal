package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.repository.ApplicantRepo;

@Repository
public class ApplicantDao {

	@Autowired
	private ApplicantRepo applicantRepo;

	public Applicant addApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	public Applicant getApplicant(long applicantId) {
		Optional<Applicant> optional = applicantRepo.findById(applicantId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
		
	}

	public void deleteApplicant(Applicant applicant) {
		applicantRepo.delete(applicant);
	}
}
