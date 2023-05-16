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
		responseStructure.setData("Failed to find the Employer with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<responseStructure<String>> ApplicantNotfoundById(ApplicantNotfoundByIdException ex){
		responseStructure<String> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData("Failed to find the Employer with requested Id!!");
		return new ResponseEntity<responseStructure<String>> (responseStructure, HttpStatus.NOT_FOUND);
	}
}
