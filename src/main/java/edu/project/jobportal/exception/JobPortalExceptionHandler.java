package edu.project.jobportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.project.jobportal.util.responseStructure;

@RestControllerAdvice
public class JobPortalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> EmployerNotFoundById(EmployerNotFoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Employer with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> ResumeNotFoundById(ResumeNotFoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Resume with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> ApplicantNotfoundById(ApplicantNotfoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Applicant with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> JobNotFoundById(JobNotFoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Job with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> SkillNotFoundById(SkillNotFoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Skill with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> SkillNotFoundByName(SkillNotFoundByNameException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Skill not present with the requested Name!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> JobNotFoundBySkill(JobNotFoundBySkill ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Job with requested Skill!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> ProjectNotFoundById(ProjectNotFoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Project with requested Skill!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
}
