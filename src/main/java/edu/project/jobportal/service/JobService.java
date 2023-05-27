package edu.project.jobportal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.EmployerDao;
import edu.project.jobportal.dao.JobDao;
import edu.project.jobportal.dao.SkillDao;
import edu.project.jobportal.dto.JobDto;
import edu.project.jobportal.dto.JobResponse;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.entity.Skill;
import edu.project.jobportal.exception.EmployerNotFoundByIdException;
import edu.project.jobportal.exception.JobNotFoundByIdException;
import edu.project.jobportal.exception.JobNotFoundBySkill;
import edu.project.jobportal.exception.SkillNotFoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SkillDao skillDao;

	public ResponseEntity<responseStructure<JobResponse>> addJob(JobDto jobDto, long employerId, String[] skills) {

		Employer employer = employerDao.getEmployer(employerId);
		if (employer != null) {
			Job job = this.modelMapper.map(jobDto, Job.class);
			job.setJobCreateDatetime(LocalDateTime.now());
			job.setEmployer(employer);
			job = jobDao.addJob(job);

			/*
			 * checking if the skill is already present in the database, if present add same
			 * skill to the job, or else create the new skill
			 */
			List<Skill> jobSkills = new ArrayList<>();
			for (String skill : skills) {
				Skill existingSkill = skillDao.getSkillByName(skill);
			
				if (existingSkill != null) {
					if (!jobSkills.contains(existingSkill)) {
						jobSkills.add(existingSkill);
					}
				} else {
					Skill newSkill = new Skill();
					newSkill.setSkillName(skill);
					skillDao.saveSkill(newSkill);
					jobSkills.add(newSkill);
				}
			}
			/*
			 * setting the exSkills list to the job*/
			job.setSkills(jobSkills);

			employer.getJobs().add(job);
			employerDao.addEmployer(employer);
			
			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> responseSkills = new ArrayList<>();
			for(Skill skill : job.getSkills()) {
				responseSkills.add(skill.getSkillName());
			}
			response.setSkills(responseSkills);

			jobDto.setJobId(job.getJobId());
			responseStructure<JobResponse> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(response);

			return new ResponseEntity<responseStructure<JobResponse>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new EmployerNotFoundByIdException("Failed to add Job!!");
		}

	}

	
	
	public ResponseEntity<responseStructure<JobResponse>> getJobById(long jobId) {
		Job job = jobDao.getJob(jobId);
		if(job!=null) {
			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> responseSkills = new ArrayList<>();
			for(Skill skill : job.getSkills()) {
				responseSkills.add(skill.getSkillName());
			}
			response.setSkills(responseSkills);
			
			responseStructure<JobResponse> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found.");
			responseStructure.setData(response);
			return new ResponseEntity<responseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);
		}else
		throw new JobNotFoundByIdException("Failed to find Job!!");
	}

	
	
	public ResponseEntity<responseStructure<List<JobResponse>>> getJobsBySkill(long skillId) {
		Skill skill = skillDao.getSkillById(skillId);
		if(skill!=null) {
			List<Job> jobs = skill.getJobs();
			List<JobResponse> responses = new ArrayList<>();
			if(!jobs.isEmpty()) {
				for(Job job : jobs) {
					JobResponse response = this.modelMapper.map(job, JobResponse.class);
					List<String> skills = new ArrayList<>();
					for(Skill s : job.getSkills()) {
						skills.add(s.getSkillName());
					}
					response.setSkills(skills);
					responses.add(response);
				}
				responseStructure<List<JobResponse>> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Job Found.");
				responseStructure.setData(responses);
				return new ResponseEntity<responseStructure<List<JobResponse>>>(responseStructure, HttpStatus.FOUND);
			}else
				throw new JobNotFoundBySkill("Failed to find Jobs!!");
			
		}else {
			throw new SkillNotFoundByIdException("Failed to find Jobs!!");
		}
	}
	
	
	
	public ResponseEntity<responseStructure<JobResponse>> updateJobById(JobDto jobDto, int jobId){
		Job exJob = jobDao.getJob(jobId);
		if(exJob!=null) {
			Job job = this.modelMapper.map(jobDto, Job.class);
			job.setJobId(jobId);
			job.setJobCreateDatetime(exJob.getJobCreateDatetime());
			job.setEmployer(exJob.getEmployer());
			job.setJobApplications(exJob.getJobApplications());
			job.setSkills(exJob.getSkills());
			job = jobDao.addJob(job);
			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> skills = new ArrayList<>();
			for(Skill skill : job.getSkills()) {
				skills.add(skill.getSkillName());
			}
			response.setSkills(skills);
			
			responseStructure<JobResponse> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Job updated successfully!!");
			responseStructure.setData(response);
			return new ResponseEntity<responseStructure<JobResponse>> (responseStructure, HttpStatus.OK);
		}else {
			throw new JobNotFoundByIdException("Failed to update Job!!");
		}
	}
	
	

	/*
	 * 1) write a method to fetch the Job buy Id, ----- fetch the job from database
	 * based on Id, check if the returned object is null or not if not null - return
	 * responseEntity, else - throw new exception;
	 * 
	 * 
	 * 2) write a method to update Job, fetch the existing Job by id, if present set
	 * the existing jobId to the updated Job(the object received in the method
	 * parameter) set the exiting jobApplication list to the updated Job entity, set
	 * the existing employer to the updated job object. then, update the job object
	 * and return response entity.
	 * 
	 * 
	 * 3) write a method to delete JOb, before deleting the job object, set job as
	 * null to all the job applications and set job as null to employer then delete
	 * the job object.
	 */
}
