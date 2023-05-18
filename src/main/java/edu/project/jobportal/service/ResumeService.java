package edu.project.jobportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dao.ResumeDao;
import edu.project.jobportal.dto.ResumeDto;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.util.responseStructure;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDao applicantDao;
	
	public ResponseEntity<responseStructure<Resume>> saveResume(long applicantId, ResumeDto resumeDto){
		
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) {
			Resume existingResume = applicant.getResume();
			Resume resume = this.modelMapper.map(resumeDto, Resume.class);
			if(existingResume!=null) {
				resume.setResumeId(existingResume.getResumeId());
			}
			resume.setApplicant(applicant);
			resume = resumeDao.saveResume(resume);
			applicant.setResume(resume);
			applicantDao.addApplicant(applicant);
			
			responseStructure<Resume> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Resume added successfully!!");
			responseStructure.setData(resume);
			return new ResponseEntity<responseStructure<Resume>> (responseStructure, HttpStatus.CREATED);
		}else {
			//throw new ApplicantNotfoundByIdException("Failed to add Resume!!");
			return null;
		}
		
		
		
	}
	
	
	/* create methods to update, fetching the resume by applicantId,
	 * */
}
