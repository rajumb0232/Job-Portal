package edu.project.jobportal.service;

import java.time.LocalDateTime;
import java.util.List;

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
import edu.project.jobportal.exception.AlreasyAppliedToJobException;
import edu.project.jobportal.exception.ApplicantNotfoundByIdException;
import edu.project.jobportal.exception.JobNotFoundByIdException;
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
				// check if the applicant has already applied to the job or not
				List<JobApplication> applications = applicant.getJobApplications();
				for(JobApplication a : applications) {
					if(a.getJob().equals(job)) {
						throw new AlreasyAppliedToJobException("Failed to add JobApplication!!");
					}
				}
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
				throw new JobNotFoundByIdException("Failed to create job Application!!");
			}
			
		}else {
			throw new ApplicantNotfoundByIdException("Failed to create Job Application!!");
		}
	}


	public ResponseEntity<responseStructure<List<JobApplication>>> getApplicationsByApplicantId(long applicantId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) {
			responseStructure<List<JobApplication>> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job Applications found by applicantId!!");
			responseStructure.setData(applicant.getJobApplications());
			return new ResponseEntity<responseStructure<List<JobApplication>>> (responseStructure, HttpStatus.CREATED);
		}else
			throw new ApplicantNotfoundByIdException("Failed to find the JobApplications!!");
	}


	public ResponseEntity<responseStructure<List<JobApplication>>> getApplicationsByJobId(long jobId) {
		Job job = jobDao.getJob(jobId);
		if(job!=null) {
			responseStructure<List<JobApplication>> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job Applications found By jobId!!");
			responseStructure.setData(job.getJobApplications());
			return new ResponseEntity<responseStructure<List<JobApplication>>> (responseStructure, HttpStatus.CREATED);
		}else
			throw new JobNotFoundByIdException("Failed to find the JobApplications!!");
	}
	

}
