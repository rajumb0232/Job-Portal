package edu.project.jobportal.service;

import java.util.Optional;

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
import edu.project.jobportal.exception.ApplicantNotfoundByIdException;
import edu.project.jobportal.exception.ResumeNotFoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDao applicantDao;

	public ResponseEntity<responseStructure<Resume>> saveResume(long applicantId, ResumeDto resumeDto) {
		/**
		 * this method is used for both save and update resume.
		 */
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if (applicant != null) {
			Resume existingResume = applicant.getResume();
			Resume resume = this.modelMapper.map(resumeDto, Resume.class);
			if (existingResume != null) {
				resume.setResumeId(existingResume.getResumeId());
				resume.setProjects(existingResume.getProjects());
				resume.setSkills(existingResume.getSkills());
			}
			resume.setApplicant(applicant);
			resume = resumeDao.saveResume(resume);
			applicant.setResume(resume);
			applicantDao.addApplicant(applicant);

			responseStructure<Resume> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Resume added/updated successfully!!");
			responseStructure.setData(resume);
			return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new ApplicantNotfoundByIdException("Failed to add Resume!!");
		}

	}

	public ResponseEntity<responseStructure<Resume>> getResumeById(long resumeId) {
		Optional<Resume> optional = resumeDao.getResumeById(resumeId);
		if(optional.isPresent()) {
			responseStructure<Resume> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Resume added/updated successfully!!");
			responseStructure.setData(optional.get());
			return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new ResumeNotFoundByIdException("Failed to find resume!!");
		}
	}


}
