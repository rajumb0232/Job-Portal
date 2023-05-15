package edu.project.jobportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dto.ApplicantDto;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.util.responseStructure;

@Service
public class ApplicatService {
	
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<responseStructure<ApplicantDto>> saveApplicant(Applicant applicant){
		
		applicant = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
		responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added successfully.");
		responseStructure.setData(applicantDto);
		return new ResponseEntity<responseStructure<ApplicantDto>> (responseStructure, HttpStatus.CREATED);
	}
}
