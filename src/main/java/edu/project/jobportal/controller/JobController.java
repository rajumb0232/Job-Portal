package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.JobDto;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.service.JobService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Job>> addJob(@RequestBody JobDto jobDto, @RequestParam long employerId){
		return jobService.addJob(jobDto, employerId);
	}
}
