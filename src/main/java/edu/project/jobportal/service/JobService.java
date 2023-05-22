package edu.project.jobportal.service;

import java.time.LocalDateTime;
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
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.entity.Skill;
import edu.project.jobportal.exception.EmployerNotFoundByIdException;
import edu.project.jobportal.exception.JobNotFoundByIdException;
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

	public ResponseEntity<responseStructure<Job>> addJob(JobDto jobDto, long employerId, String[] skills) {

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
			List<Skill> exSkills = job.getSkills();
			for (String skill : skills) {
				Skill existingSkill = skillDao.getSkillByName(skill);
			
				if (existingSkill != null) {
					if (!exSkills.contains(existingSkill)) {
						exSkills.add(existingSkill);
					}
				} else {
					Skill newSkill = new Skill();
					newSkill.setSkillName(skill);
					skillDao.saveSkill(newSkill);
					exSkills.add(newSkill);
				}
			}
			/*
			 * setting the exSkills list to the job*/
			job.setSkills(exSkills);

			employer.getJobs().add(job);
			employerDao.addEmployer(employer);

			jobDto.setJobId(job.getJobId());
			responseStructure<Job> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(job);

			return new ResponseEntity<responseStructure<Job>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new EmployerNotFoundByIdException("Failed to add Job!!");
		}

	}

	public ResponseEntity<responseStructure<Job>> getJobById(long jobId) {
		Job job = jobDao.getJob(jobId);
		if(job!=null) {
			
		}else
		throw new JobNotFoundByIdException("Failed to find Job!!");
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
