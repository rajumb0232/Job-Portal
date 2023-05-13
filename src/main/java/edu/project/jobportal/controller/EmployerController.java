package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.service.EmployerService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Employer>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
}
