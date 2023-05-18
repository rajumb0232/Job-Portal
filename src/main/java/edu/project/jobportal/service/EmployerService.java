package edu.project.jobportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.EmployerDao;
import edu.project.jobportal.dao.JobDao;
import edu.project.jobportal.dto.EmployerDto;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.exception.EmployerNotFoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class EmployerService {
	
	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JobDao jobDao;

	public ResponseEntity<responseStructure<EmployerDto>> addEmployer(Employer employer) {
		Employer employer2 = employerDao.addEmployer(employer);
		EmployerDto employerDto = this.modelMapper.map(employer2, EmployerDto.class);
		responseStructure<EmployerDto> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added successfully.");
		responseStructure.setData(employerDto);
		return new ResponseEntity<responseStructure<EmployerDto>> (responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructure<EmployerDto>> getEmployerById(int employerId) {
		Employer employer = employerDao.getEmployer(employerId);
		if (employer != null) {
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
			responseStructure<EmployerDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND);
		}
		throw new EmployerNotFoundByIdException("Failed to find Employer!!");
	}

	public ResponseEntity<responseStructure<EmployerDto>> updateEmployer(int employerId, Employer employer) {
		Employer exEmployer = employerDao.getEmployer(employerId);
		if (exEmployer != null) {
			employer.setEmployerId(exEmployer.getEmployerId());
			employer.setJobs(exEmployer.getJobs());
			// add employer method used to update
			employer = employerDao.addEmployer(employer);
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
			responseStructure<EmployerDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer updated successfully.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		}
		throw new EmployerNotFoundByIdException("Failed to update Employer!!");
	}

	public ResponseEntity<responseStructure<EmployerDto>> deleteEmployer(int employerId) {
		Employer employer = employerDao.getEmployer(employerId);
		if (employer != null) {
			/* while deleting the employer, the jobs that he created are not
			   deleted rather the employer has to null in job.*/
			for(Job job : employer.getJobs()) {
				job.setEmployer(null);
				jobDao.addJob(job);
			}
			// add employer method used to update
			employerDao.deleteEmployer(employer);
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
			responseStructure<EmployerDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer updated successfully.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		}
		throw new EmployerNotFoundByIdException("Failed to update Employer!!");
	}
	
	
	
	
}
