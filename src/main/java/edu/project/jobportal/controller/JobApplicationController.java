package edu.project.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.service.JobApplicationService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

	@Autowired
	private JobApplicationService applicationService;
	
	@PostMapping
	public ResponseEntity<responseStructure<JobApplication>> createJobApplication(
			@RequestParam long applicantId, @RequestParam long jobId){
		return applicationService.createJobApplication(applicantId, jobId);
	}
	
	@GetMapping("/applicant")
	public ResponseEntity<responseStructure<List<JobApplication>>> getApplicationsByApplicantId(
			@RequestParam long applicantId){
		return applicationService.getApplicationsByApplicantId(applicantId);
	}
	
	@GetMapping("/job")
	public ResponseEntity<responseStructure<List<JobApplication>>> getApplicationsByJobId(
			@RequestParam long jobId){
		return applicationService.getApplicationsByJobId(jobId);
	}
}
