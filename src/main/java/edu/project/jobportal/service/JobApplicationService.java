package edu.project.jobportal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dao.JobApplicationDao;
import edu.project.jobportal.dao.JobDao;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.exception.ApplicantNotfoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class JobApplicationService {
	
	@Autowired
	private JobApplicationDao jobApplicationDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private JobDao jobDao;
	
	
	public ResponseEntity<responseStructure<JobApplication>> createJobApplication(
			long applicantId, long jobId){
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) {
			
			Job job = jobDao.getJob(jobId);
			if(job!=null) {
				JobApplication application = new  JobApplication();
				application.setJobApplicationDateTime(LocalDateTime.now());
				application.setJob(job);
				application.setApplicant(applicant);
				
				// saving the jobApplication object
				application = jobApplicationDao.createJobApplication(application);
				
				// setting and updating jobApplication for the job
				job.getJobApplications().add(application);
				jobDao.addJob(job);
				
				//  setting and updating jobApplication for the Applicant
				applicant.getJobApplications().add(application);
				applicantDao.addApplicant(applicant);
				
				responseStructure<JobApplication> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added Successfully!!");
				responseStructure.setData(application);
				return new ResponseEntity<responseStructure<JobApplication>> (responseStructure, HttpStatus.CREATED);
				
			}else {
				//throw new jobNotFoundByIdException("Failed to create job Application!!");
				return null;
			}
			
		}else {
			throw new ApplicantNotfoundByIdException("Failed to create Job Application!!");
		}
	}
}
