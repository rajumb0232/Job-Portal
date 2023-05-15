package edu.project.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dao.ResumeDao;
import edu.project.jobportal.dao.SkillDao;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.entity.Skill;
import edu.project.jobportal.util.responseStructure;

@Service
public class SkillService {
	
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDao resumeDao;
	
	public ResponseEntity<responseStructure<Resume>> saveSkill(long applicantId ,String[] skills){
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) {
			Resume resume = applicant.getResume();
			if(resume!=null) {
			/*
			 * - iterate over the String arrays skills that is recieved
			 * check if the skill is present with matching name,
			 * if present add the existing skill to the resume
			 * or else create an new skill
			 * */
				
				for(String skill : skills) {
					Skill existingskill = skillDao.getSkillByName(skill);
					if(!resume.getSkills().contains(existingskill)) {
						if(existingskill!=null) {
							resume.getSkills().add(existingskill);
						}else {
							Skill newSkill = new Skill();
							newSkill.setSkillName(skill);
							resume.getSkills().add(newSkill);
						}
					}
					
				}
				
				resume = resumeDao.saveResume(resume);
				responseStructure<Resume> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Resume added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<responseStructure<Resume>> (responseStructure, HttpStatus.CREATED);
				
			}else {
				//throw new ResumeNotFoundByIdException("Failed to add skills!!");
				return null;
			}
		}else {
			//throw new ApplicantNotfoundByIdException("Failed to add Resume!!");
			return null;
		}
	}
}
