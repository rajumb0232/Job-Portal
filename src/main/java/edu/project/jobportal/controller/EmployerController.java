package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.EmployerDto;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.service.EmployerService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<responseStructure<EmployerDto>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<EmployerDto>> getEmployerById(@RequestParam int employerId){
		return employerService.getEmployerById(employerId);
	}
	
	@PutMapping
	public ResponseEntity<responseStructure<EmployerDto>> updateApplicant(@RequestParam int employerId, @RequestBody Employer employer){
		return employerService.updateEmployer(employerId, employer);
	}
	
	@DeleteMapping
	public ResponseEntity<responseStructure<EmployerDto>> deleteApplicantById(@RequestParam int employerId){
		return employerService.deleteEmployer(employerId);
	}
}
