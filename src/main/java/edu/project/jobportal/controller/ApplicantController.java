package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.ApplicantDto;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.service.ApplicatService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicatService  applicatService;
	
	@PostMapping
	public ResponseEntity<responseStructure<ApplicantDto>> saveApplicant(@RequestBody Applicant applicant){
		return applicatService.saveApplicant(applicant);
	}
}
