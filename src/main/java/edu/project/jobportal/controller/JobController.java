package edu.project.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.JobDto;
import edu.project.jobportal.dto.JobResponse;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.service.JobService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<responseStructure<JobResponse>> addJob(
			@RequestBody JobDto jobDto, @RequestParam long employerId, @RequestParam String[] skills){
		return jobService.addJob(jobDto, employerId, skills);
	}

	@GetMapping
	public ResponseEntity<responseStructure<JobResponse>> getJobById(@RequestParam long jobId){
		return jobService.getJobById(jobId);
	}
	
	@GetMapping("/skill")
	public ResponseEntity<responseStructure<List<Job>>> getJobsBySkill(
			@RequestParam long skillId){
		return jobService.getJobsBySkill(skillId);
	}
}
