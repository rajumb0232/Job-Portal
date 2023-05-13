package edu.project.jobportal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.EmployerDao;
import edu.project.jobportal.dao.JobDao;
import edu.project.jobportal.dto.JobDto;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.util.responseStructure;

@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;
	
	public ResponseEntity<responseStructure<Job>> addJob(JobDto jobDto, long employerId){
		
		Employer employer = employerDao.getEmployer(employerId);
		if(employer!=null) {
			Job job = new Job();
			job.setJobTitle(jobDto.getJobTitle());
			job.setJobDiscription(jobDto.getJobDiscription());
			job.setCompany(jobDto.getCompany());
			job.setSalary(jobDto.getSalary());
			job.setJobCreateDatetime(LocalDateTime.now());
			job.setEmployer(employer);
			job = jobDao.addJob(job);
			
			employer.getJobs().add(job);
			employerDao.addEmployer(employer);
			
			jobDto.setJobId(job.getJobId());
			responseStructure<Job> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(jobDto);
			
			return new ResponseEntity<responseStructure<Job>> (responseStructure, HttpStatus.CREATED);
		}else {
			// throw exception EmployerNotFoundByIdException
			return null;
		}
		
		
		
		
	}
}
